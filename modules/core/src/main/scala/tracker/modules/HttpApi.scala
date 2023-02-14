package tracker.modules
import cats.MonadThrow
import cats.effect.IO
import cats.effect.kernel.Clock
import org.http4s.HttpApp
import org.typelevel.log4cats.Logger
import tracker.auth.{AsymmetricKeyPair, JwtMiddleware, Token}
import tracker.config.TokenKeyPair
import tracker.domain.user.User
import tracker.routes.{Activity, Greeter}
import cats.syntax.all.*

class HttpApi[F[_]: MonadThrow : Logger : Clock](keyPair :TokenKeyPair) :

  private val pair = AsymmetricKeyPair( keyPair.privateKey,keyPair.publicKey)
  private val token = Token.makeAsymToken[F, User](pair)
  private val middleware = JwtMiddleware.middleware[F, User](token)

  private val greeterRoutes = Greeter[F].routes(middleware)
  private val activityRoutes = Activity[F].routes(middleware)

  private val allRoutes = greeterRoutes <+> activityRoutes

  def httpApp: HttpApp[F] = allRoutes.orNotFound