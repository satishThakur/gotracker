package tracker.domain

import java.util.UUID

object user:

  opaque type Id = UUID

  case class User(id: Id, email: String)
