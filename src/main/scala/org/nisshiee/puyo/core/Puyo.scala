package org.nisshiee.puyo.core

import scalaz._, Scalaz._

sealed trait Puyo
case object Blue extends Puyo
case object Red extends Puyo
case object Green extends Puyo
case object Yellow extends Puyo
case object Purple extends Puyo
case object Ojama extends Puyo

import Storage.PuyoType

object Puyo {
  def puyoSJ: Puyo => PuyoType = {
    case Blue => PuyoType.BLUE_PUYO
    case Red => PuyoType.RED_PUYO
    case Green => PuyoType.GREEN_PUYO
    case Yellow => PuyoType.YELLOW_PUYO
    case Purple => PuyoType.PURPLE_PUYO
    case Ojama => PuyoType.OJAMA_PUYO
  }

  def puyoJS: PuyoType => Puyo = {
    case PuyoType.BLUE_PUYO => Blue
    case PuyoType.RED_PUYO => Red
    case PuyoType.GREEN_PUYO => Green
    case PuyoType.YELLOW_PUYO => Yellow
    case PuyoType.PURPLE_PUYO => Purple
    case PuyoType.OJAMA_PUYO => Ojama
  }

  val colors = List(Blue, Red, Green, Yellow, Purple)
}

object Puyos {
  implicit lazy val PuyoShow = shows[Puyo] {
    case Blue => "青"
    case Red => "赤"
    case Green => "緑"
    case Yellow => "黄"
    case Purple => "紫"
    case Ojama => "○"
  }

  implicit lazy val PuyoEqual = equalA[Puyo]
}
