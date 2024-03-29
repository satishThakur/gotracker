package tracker.config

import com.comcast.ip4s.*
import ciris.*
import io.circe.refined.*
import cats.effect.kernel.Async
import cats.syntax.all.*

final case class Config(
    port: Port,
    tokenKeyPair: TokenKeyPair
)

final case class TokenKeyPair(
    publicKey: String,
    privateKey: String
)

object Config:
  given ConfigDecoder[String, Port] =
    ConfigDecoder[String].mapOption("com.comcast.ip4s.Port")(Port.fromString)

  def load[F[_]: Async]: F[Config] =
    env("HTTP_PORT")
      .as[Port]
      .default(port"8085")
      .load[F]
      .map(Config(_, TokenKeyPair("", "")))


