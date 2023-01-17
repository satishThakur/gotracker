package tracker.domain

import java.time.LocalDateTime
import java.util.UUID

object activity:

  opaque type Id = UUID

  enum State:
    case Created
    case InProgress
    case Paused
    case Completed

  case class Timeline(created: LocalDateTime, started: Option[LocalDateTime], completed: Option[LocalDateTime])

  case class Progress(percentCompleted: Int)

  case class ActivityState(timeline: Timeline, progress: Progress)

  sealed trait Activity:
    def id: Id
    def state: ActivityState

  object Activity:
    case class BookReading(id: Id, name: String, description: String, state: ActivityState) extends Activity
