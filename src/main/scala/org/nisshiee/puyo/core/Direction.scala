package org.nisshiee.puyo.core

import scalaz._, Scalaz._

import jp.ac.nagoya_u.is.ss.kishii.usui.system.game.Puyo.PuyoDirection

object Directions {
  implicit lazy val DirectionShow = shows[Direction] {
    case Up => "↑"
    case Down => "↓"
    case Right => "→"
    case Left => "←"
  }
}

sealed trait Direction
case object Up extends Direction
case object Down extends Direction
case object Right extends Direction
case object Left extends Direction

object Direction {
  def directionSJ: Direction => PuyoDirection = {
    case Up => PuyoDirection.UP
    case Down => PuyoDirection.DOWN
    case Right => PuyoDirection.RIGHT
    case Left => PuyoDirection.LEFT
  }
}
