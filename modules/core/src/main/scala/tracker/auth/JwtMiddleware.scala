package tracker.auth

import cats.data.{Kleisli, OptionT}
import org.http4s.{AuthedRequest, Request, Response, Status}
import org.http4s.server.AuthMiddleware
import org.typelevel.ci.CIString

object JwtMiddleware:

  def authUser[F[_], U](token: Token[F,U]): Kleisli[OptionT[F,_], Request[F], U] = Kleisli {
    req =>
      authHeaderValue(req).map {
        case Some(t) => token.verify(t)
        case None => None
      }
  }

  def authHeaderValue[F[_]](req: Request[F]): Option[String] =
    req.headers.get(CIString("Authorization")).map {
      ls => ls.head.value
    }

  def middleware[F[_], U](authUser: Kleisli[OptionT[F,_], Request[F], U]): AuthMiddleware[F, U] =
    service => Kleisli {
      req =>
        OptionT(userAuth(req).value.flatMap {
          case Some(a) => service(AuthedRequest(a, req)).value
          case None => Some(Response(Status.Unauthorized)).pure[F]
        })
    }
