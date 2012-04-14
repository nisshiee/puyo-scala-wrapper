package org.nisshiee.puyo.core

import scalaz._, Scalaz._

class Board private(
  val field: Field,
  val current: PuyoBlock,
  val next: PuyoBlock,
  val nextNext: PuyoBlock,
  val ojamaQueue: List[Int]
) {
}
