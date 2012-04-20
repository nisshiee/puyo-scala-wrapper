package org.nisshiee.puyo.core

import scalaz._, Scalaz._

import jp.ac.nagoya_u.is.ss.kishii.usui.system.game.{ Puyo => JPuyoBlock }
import jp.ac.nagoya_u.is.ss.kishii.usui.system.game.Puyo.PuyoNumber

case class PuyoBlock(base: Puyo, sub: Puyo)

object PuyoBlocks {
  import Puyos._

  implicit lazy val PuyoBlockShow = shows[PuyoBlock] {
    case PuyoBlock(base, sub) => "(" |+| base.shows |+| sub.shows |+| ")"
  }

  implicit lazy val PuyoBlockEqual = equalA[PuyoBlock]
}

object PuyoBlock {
  def puyoBlockJS: JPuyoBlockWrapper => PuyoBlock = {
    j => PuyoBlock(Puyo.puyoJS(j.base), Puyo.puyoJS(j.sub))
  }
}

private[core] class JPuyoBlockWrapper(val j: JPuyoBlock) {
  def base = j.getPuyoType(PuyoNumber.FIRST)
  def sub = j.getPuyoType(PuyoNumber.SECOND)
}
