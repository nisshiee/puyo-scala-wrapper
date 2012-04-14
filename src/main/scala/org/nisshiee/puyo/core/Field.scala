package org.nisshiee.puyo.core

import scalaz._, Scalaz._

class Field private(
  val width: Int,
  val height: Int,
  val deadLine: Int,
  val puyos: Map[InFieldPoint, Puyo]
) {
  def apply(ifp: InFieldPoint) = puyos get ifp
}
