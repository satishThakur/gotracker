package tracker
import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.ember.server.EmberServerBuilder
import tracker.routes.Greeter
import com.comcast.ip4s.*
import org.http4s.server.Server
import org.http4s.server.defaults.Banner
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

object Main extends IOApp:
  implicit val logger : Logger[IO] = Slf4jLogger.getLogger[IO]

  private def showEmberBanner[F[_] : Logger](s: Server): F[Unit] =
    Logger[F].info(s"\n${Banner.mkString("\n")}\nHTTP Server started at ${s.address}")

  override def run(args: List[String]): IO[ExitCode] =
    val apiApp = Greeter[IO].routes.orNotFound
    (for {
      _ <- EmberServerBuilder
        .default[IO]
        .withHost(ipv4"0.0.0.0")
        .withPort(port"8055")
        .withHttpApp(apiApp)
        .build
        .evalTap(showEmberBanner[IO])
        .use(_ => IO.never)

    } yield ()).as(ExitCode.Success)
