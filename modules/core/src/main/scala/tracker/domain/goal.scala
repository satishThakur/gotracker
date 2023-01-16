package tracker.domain

import java.time.{LocalDate, LocalDateTime}
import tracker.domain.activity.Activity
import java.util.UUID

object goal:

  opaque type Id = UUID

  object Id:
    def apply(uuid: UUID): Id = uuid

  enum State:
    case Created
    case Started
    case Completed

  case class Timeline(created: LocalDateTime, started: LocalDateTime,
                      completed: Option[LocalDateTime])

  case class Progress(percentCompleted: Int)

  case class Goal(id: Id, name: String, description: String, state: State,
                  timeline: Timeline, progress: Progress, activities: List[Activity])


  case class CreateGoal(name: String, description: String, completeDate: LocalDateTime)
