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
import org.http4s.server.Router

class Greeter[F[_]: Monad] extends Http4sDsl[F]:

  private val prefix = "rest"

  private val greetingsRoute : HttpRoutes[F] = HttpRoutes.of[F]{
    case GET -> Root / "goals" => Ok("hello there")
  }

  val routes: HttpRoutes[F] = Router(
    prefix -> greetingsRoute
  )
