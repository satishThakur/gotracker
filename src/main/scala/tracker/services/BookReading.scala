package tracker.services

trait BookReading[F[_]] extends Activities[F]:
  def updateReadingProgress(pageNumber: Int): F[Unit]
