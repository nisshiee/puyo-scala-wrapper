package org.nisshiee.puyo.core

import scalaz._
import Scalaz._

// TODO: たぶんこれもcase classやめてコンストラクタの隠ぺいが必要
case class Field(
  width: Int,
  height: Int,
  puyos: Map[InFieldPoint, Puyo]
) {
  def apply(ifp: InFieldPoint) = puyos get ifp
}
