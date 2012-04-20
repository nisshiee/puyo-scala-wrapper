package org.nisshiee.puyo.core

import scalaz._, Scalaz._

object InFieldPoints {
  import Points._

  implicit lazy val InFieldPointShow = showBy[InFieldPoint, Point](_.p)

  implicit lazy val InFieldPointEqual = equalBy[InFieldPoint, Point](_.p)
}

class InFieldPoint private[core] (val p: Point) {
  import InFieldPoints._
  
  override def toString = this.shows

  override def equals(o: Any) = o match {
    case ifp: InFieldPoint => this ≟ ifp
    case _ => false
  }

  override def hashCode = p.hashCode
}

object InFieldPoint {
  def check(p: Point, f: Field) =
    (0 <= p.x, p.x < f.width, 0 <= p.y, p.y < f.height) match {
      case (true, true, true, true) => new InFieldPoint(p).some
      case _ => none[InFieldPoint]
    }

  def unapply(ifp: InFieldPoint) = ifp.p.some
}
