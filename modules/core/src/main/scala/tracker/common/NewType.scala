package tracker.common
import cats.{Eq, Show}
import io.circe.{ Decoder, Encoder }

abstract class NewType[T](using eq: Eq[T],
                          show: Show[T],
                          encoder: Encoder[T],
                          decoder: Decoder[T]):
  opaque type Type = T

  given Eq[Type] = eq
  given Show[Type] = show
  given Encoder[Type] = encoder
  given Decoder[Type] = decoder

  extension (t: Type) inline def value: T = t

  inline def apply(value: T): Type = value
