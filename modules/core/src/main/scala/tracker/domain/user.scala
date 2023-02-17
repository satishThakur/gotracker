package tracker.domain

import java.util.UUID
import io.circe.Codec
import tracker.common.NewType
import io.circe.syntax.*

object user:

  object Id extends NewType[UUID]
  type Id = Id.Type

  object Email extends NewType[String]
  type Email = Email.Type

  case class User(id: Id, email: Email) derives Codec.AsObject
