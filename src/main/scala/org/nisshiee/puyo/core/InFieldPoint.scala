package org.nisshiee.puyo.core

import scalaz._, Scalaz._

trait InFieldPoints extends Points {

  implicit lazy val InFieldPointShow = showBy[InFieldPoint, Point](_.p)

  implicit lazy val InFieldPointEqual = equalBy[InFieldPoint, Point](_.p)
}

case class InFieldPoint private[core] (val p: Point) extends InFieldPoints {

  override def toString = this.shows
}

object InFieldPoint {
  def check(p: Point, f: Field) =
    (0 <= p.x, p.x < f.width, 0 <= p.y, p.y < f.height) match {
      case (true, true, true, true) => InFieldPoint(p).some
      case _ => none[InFieldPoint]
    }
}
