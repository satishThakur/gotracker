package tracker.effects

import java.util.UUID
import cats.effect.Sync

/**
 * TypeClass to generate random UUID..
 * @tparam F - effect type
 */
trait GenUUID[F[_]]:
  def generate: F[UUID]


object GenUUID:

  //def apply[F[_]](using g: GenUUID[F]): GenUUID[F] = g
  //this saves us from writing summon[GenUUID[F]] --> GenUUID[F]
  def apply[F[_] : GenUUID]: GenUUID[F] = summon

  //if we already have Sync[F] in context we get GenUUID[F] for free..
  given uuid[F[_] : Sync]: GenUUID[F] = new GenUUID[F]:
    def generate: F[UUID] = Sync[F].delay(UUID.randomUUID())
