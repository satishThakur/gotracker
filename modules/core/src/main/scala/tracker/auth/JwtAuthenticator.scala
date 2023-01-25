package tracker.auth

import org.http4s.server.AuthMiddleware
import tracker.domain.user.User

/**
 * JwtAuthenticator is a class that provides authentication using JWT.
 * Provides a method which takes a JWT and returns a User object.
 */
class JwtAuthenticator[F[_]] {

  def authenticate(jwt: String): F[Option[User]] = ???

  def generateToken(user: User): F[String] = ???

}

object JwtAuthenticator:
  def apply[F[_]](using JwtAuthenticator[F]): JwtAuthenticator[F] = summon[JwtAuthenticator[F]]

  def middleware[F[_]: JwtAuthenticator]: AuthMiddleware[F, User] = ???
      