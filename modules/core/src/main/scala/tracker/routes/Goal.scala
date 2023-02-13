package tracker.routes

import cats.Monad
import org.http4s.dsl.Http4sDsl
import org.typelevel.log4cats.Logger

/** Basic routes to define. Create a goal - Input - user and Input to create a goal. Add a Activity to Goal Remove
  * activity from the goal. Get one or more goals for a user.
  */
class Goal[F[_]: Monad : Logger] extends Http4sDsl[F]:

  private val prefix = "rest"
