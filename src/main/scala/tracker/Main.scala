package tracker
import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.ember.server.EmberServerBuilder
import tracker.routes.Greeter
import com.comcast.ip4s.*

object Main extends IOApp :

  override def run(args: List[String]): IO[ExitCode] =
    val apiApp = Greeter[IO].routes.orNotFound
    (for{
      _ <- EmberServerBuilder
        .default[IO]
        .withHost(ipv4"0.0.0.0")
        .withPort(port"8055")
        .withHttpApp(apiApp)
        .build
        .use(_ => IO.never)
    } yield ()).as(ExitCode.Success)
