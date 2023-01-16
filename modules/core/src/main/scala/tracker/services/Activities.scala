package tracker.services
import tracker.domain.activity.{Activity, Id}
import tracker.domain.goal
import tracker.domain.user

trait Activities[F[_]]:
  def findAll(id : goal.Id) : F[List[Activity]]

  def start(id : Id): F[Activity]

  def complete(id: Id): F[Activity]

  def pause(id: Id): F[Activity]

  def create(userid: user.Id): F[Activity]
