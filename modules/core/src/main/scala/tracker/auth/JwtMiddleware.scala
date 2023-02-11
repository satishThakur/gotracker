package tracker.auth

import cats.MonadThrow
import cats.data.{Kleisli, OptionT}
import org.http4s.{AuthedRequest, Request, Response, Status}
import org.http4s.server.AuthMiddleware
import org.typelevel.ci.CIString
import cats.syntax.all.*

object JwtMiddleware:

  private def authUser[F[_] : MonadThrow, U](token: Token[F, U]): Kleisli[OptionT[F,_], Request[F], U] =
    val header : Kleisli[OptionT[F,_], Request[F], String] = Kleisli {
      req => OptionT(authHeaderValue(req).pure[F])
    }
    val userFunc: Kleisli[OptionT[F,_], String, U] = Kleisli {
      s => OptionT(token.verify(s).map(Some(_)).handleErrorWith(_ => None.pure[F]))
    }
    header.andThen(userFunc)

  private def authHeaderValue[F[_]](req: Request[F]): Option[String] =
    req.headers.get(CIString("Authorization")).map {
      ls => ls.head.value
    }

  def middleware[F[_]: MonadThrow, U](authUser: Kleisli[OptionT[F,_], Request[F], U]): AuthMiddleware[F, U] =
    service => Kleisli {
      req =>
        OptionT(authUser(req).value.flatMap {
          case Some(a) => service(AuthedRequest(a, req)).value
          case None => Some(Response(Status.Unauthorized)).pure[F]
        })
    }
