package tracker.services

import tracker.domain.goal.{CreateGoal, Goal, Id, State, Timeline, Progress}
import tracker.effects.GenUUID
import cats.effect.Ref
import cats.Functor
import cats.syntax.functor.*
import tracker.domain.user

import java.time.LocalDateTime
import java.util.UUID

trait Goals[F[_]]:

  def findAll(userId: user.Id) : F[List[Goal]]

  def find(userId: user.Id, id: Id) : F[Option[Goal]]

  def create(createReq: CreateGoal) : F[Goal]



object Goals:
  def makeInMem[F[_] : Functor: GenUUID](data : Ref[F, Map[user.Id, List[Goal]]]): Goals[F] = new Goals[F]:

    def findAll(userId: user.Id) : F[List[Goal]] = data.get.map(_.getOrElse(userId, Nil))

    def find(userId: user.Id, id: Id) : F[Option[Goal]] =
      findAll(userId).map(_.find(_.id == id))

    def create(createReq: CreateGoal) : F[Goal] =
      val id : F[Id] = GenUUID[F].generate.map(Id(_))
      val now: LocalDateTime = LocalDateTime.now()
      id.map(id => Goal(id, createReq.name, createReq.description,
        State.Created, Timeline(now, now, None),Progress(0), Nil))



