package org.nisshiee.puyo.core

import scalaz._, Scalaz._

import jp.ac.nagoya_u.is.ss.kishii.usui.system.game.{ Field => JField }
import jp.ac.nagoya_u.is.ss.kishii.usui.system.storage.PuyoType

trait Fields {
  implicit def fieldWrap: JField => JFieldWrapper = new JFieldWrapper(_)
}

case class Field private (
  val width: Int,
  val height: Int,
  val deadLine: Int,
  val puyos: Map[InFieldPoint, Puyo]
) {
  def apply(ifp: InFieldPoint) = puyos get ifp
}

object Field {
  def fieldJS(jw: JFieldWrapper): Field = {
    val puyoMap = (for {
      x <- 0 until jw.width
      y <- 0 until jw.height
    } yield (x, y, InFieldPoint(Point(x, y)))) ∘ {
      case (x, y, p) => Option(jw.puyoType(x, y)) ∘ Puyo.puyoJS ∘ (p -> _)
    } |> (_.flatten.toMap)

    Field(jw.width, jw.height, jw.deadLine, puyoMap)
  }
}

private[core] class JFieldWrapper(val j: JField) {
  def width = j.getWidth
  def height = j.getHeight
  def deadLine = j.getDeadLine
  def puyoType(x: Int, y: Int) = j.getPuyoType(x, y)
}
