package tracker.routes

import cats.effect.Concurrent
import cats.effect.kernel.Sync
import cats.{Monad, MonadThrow}
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.{AuthMiddleware, Router}
import org.http4s.circe.JsonDecoder
import org.http4s.circe.CirceEntityEncoder.*
import org.http4s.circe.CirceEntityDecoder.*
import org.typelevel.log4cats.Logger
import tracker.domain.activity.Activity.BookReadingRequest
import tracker.domain.user.User
import io.circe.syntax.*
import cats.syntax.all.*

/** Create a new activity for a particular user. Pause an activty. Finish an activity.
  */
class Activity[F[_]: MonadThrow: Concurrent : Logger : JsonDecoder] extends Http4sDsl[F]:

  private val prefix = "rest"

  private val createActivity = HttpRoutes.of[F] {
    case req @ POST -> Root / "activity" =>
      for{
        activity <- req.as[BookReadingRequest]
        _ <- Logger[F].info(s"activity : $activity")
        resp <- Ok(activity.asJson)
      }yield resp
  }

  def routes(authMiddleware: AuthMiddleware[F, User]): HttpRoutes[F] = Router(
    prefix + "/other" -> createActivity,
  )






