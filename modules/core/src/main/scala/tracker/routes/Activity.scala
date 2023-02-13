package tracker.routes

import cats.Monad
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.{AuthMiddleware, Router}
import org.typelevel.log4cats.Logger
import tracker.domain.activity.Activity.BookReadingRequest
import tracker.domain.user.User

/** Create a new activity for a particular user. Pause an activty. Finish an activity.
  */
class Activity[F[_]: Monad : Logger] extends Http4sDsl[F]:

  private val prefix = "rest"

  private val createActivity = HttpRoutes.of[F] {
    case req @ POST -> Root / `prefix` / "activity" =>
      for {
        _ <- Logger[F].info("Create activity")
        _ <- req.decode[BookReadingRequest] { activity =>
          Logger[F].info(s"Create activity ${activity}")
        }
      } yield Ok()
  }

  def routes(authMiddleware: AuthMiddleware[F, User]): HttpRoutes[F] = Router(
    prefix + "/auth" -> createActivity,
  )






