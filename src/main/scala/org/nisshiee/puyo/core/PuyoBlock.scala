package org.nisshiee.puyo.core

import scalaz._, Scalaz._

case class PuyoBlock(base: Puyo, sub: Puyo)

object PuyoBlocks {
  import Puyos._

  implicit lazy val PuyoBlockShow = shows[PuyoBlock] {
    case PuyoBlock(base, sub) => "(" |+| base.shows |+| sub.shows |+| ")"
  }

  implicit lazy val PuyoBlockEqual = equalA[PuyoBlock]
}
