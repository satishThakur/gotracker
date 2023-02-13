package tracker.routes

import org.http4s.*
import org.http4s.dsl.io.*
import org.http4s.implicits.*
import cats.effect.IO
import org.http4s.circe.CirceEntityCodec.*
import cats.Monad
import cats.syntax.all.*
import org.http4s.dsl.Http4sDsl
import org.http4s.server
import org.http4s.server.{AuthMiddleware, Router}
import org.typelevel.log4cats.Logger
import tracker.domain.user.User

class Greeter[F[_]: Monad : Logger] extends Http4sDsl[F]:

  private val prefix = "rest"

  private val greetingsRoute: HttpRoutes[F] = HttpRoutes.of[F] { case GET -> Root / name =>
    Logger[F].info("sending greeting back.") *> Ok(s"hello there - $name")
  }

  private val authGreetingRoute : AuthedRoutes[User, F] = AuthedRoutes.of[User, F] {
    case GET -> Root / "goals" as user =>
      Logger[F].info("sending greeting back.") *> Ok(s"hello there $user")
  }

  def routes(authMiddleware: AuthMiddleware[F, User]): HttpRoutes[F] = Router(
    prefix + "/goals" -> greetingsRoute,
    prefix + "/auth"  -> authMiddleware(authGreetingRoute)
  )
