package tracker.domain

import java.util.UUID

object user:

  opaque type Id = UUID

  opaque type Email = String

  case class User(id: Id, email: Email)
