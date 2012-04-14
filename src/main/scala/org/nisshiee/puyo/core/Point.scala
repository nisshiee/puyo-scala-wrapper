package org.nisshiee.puyo.core

import scalaz._
import Scalaz._

object Points {
  implicit lazy val PointShow = shows[Point] {
    case Point(x, y) => "(" |+| x.shows |+| ", " |+| y.shows |+| ")"
  }

  implicit lazy val PointEqual = equalA[Point]

  implicit lazy val PointSemigroup = semigroup[Point] {
    (_, _) match {
      case (Point(x1, y1), Point(x2, y2)) => Point(x1 |+| x2, y1 |+| y2)
    }
  }

  implicit lazy val PointZero = zero[Point](Point(0, 0))
}

case class Point(x: Int, y: Int) {
  import Points._
  
  def +(p: => Point) = this |+| p

  def unary_- = Point(-this.x, -this.y)

  def -(p: => Point) = this |+| -p

  def in(implicit f: Field) = InFieldPoint(f, this)
}
