package org.nisshiee.puyo.core

import scalaz._, Scalaz._

import scala.collection.JavaConversions

import jp.ac.nagoya_u.is.ss.kishii.usui.system.game.{ Board => JBoard }

case class Board private (
  val field: Field,
  val current: PuyoBlock,
  val next: PuyoBlock,
  val nextNext: PuyoBlock,
  val ojamaQueue: List[Int]
) {
}

object Board {
  def boardJS: JBoardWrapper => Board = { j =>
    Board(
      Field.fieldJS(j.field),
      PuyoBlock.puyoBlockJS(j.currentPuyo),
      PuyoBlock.puyoBlockJS(j.nextPuyo),
      PuyoBlock.puyoBlockJS(j.nextNextPuyo),
      JavaConversions.asScalaBuffer(j.ojamaList).toList ∘ (i => i: Int))
  }
}

private[core] class JBoardWrapper(val j: JBoard) {
  def currentPuyo = new JPuyoBlockWrapper(j.getCurrentPuyo)
  def nextPuyo = new JPuyoBlockWrapper(j.getNextPuyo)
  def nextNextPuyo = new JPuyoBlockWrapper(j.getNextNextPuyo)
  def field = new JFieldWrapper(j.getField)
  def ojamaList = j.getNumbersOfOjamaList
}
