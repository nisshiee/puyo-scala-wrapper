package org.nisshiee.puyo.core

import scalaz._, Scalaz._

import Directions._

import jp.ac.nagoya_u.is.ss.kishii.usui.system.game.{ Action => JAction }

object Actions {
  implicit lazy val ActionShow = shows[Action] { a =>
    a.direction.shows |+| a.col.toString
  }

  implicit lazy val ActionEqual = equalBy[Action, (Direction, Int)] { a =>
    (a.direction, a.col)
  }
}

case class Action private (
  val direction: Direction,
  val col: Int
) {
}

object Action {
  def check(d: Direction, c: Int)(implicit f: Field) = {
    def range: Direction => (Int, Int) = {
      case Up => (0, f.width)
      case Down => (0, f.width)
      case Right => (0, f.width - 1)
      case Left => (1, f.width)
    }

    range(d) |> { case (b, t) => (c gte b) && (c lt t) } |> {
      case true => Action(d, c).some
      case false => none
    }
  }

  def actionSJ: Action => JAction = {
    case Action(d, c) => new JAction(Direction.directionSJ(d), c)
  }
}
