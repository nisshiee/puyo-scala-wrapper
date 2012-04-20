package org.nisshiee.puyo.core

import org.specs2._, mock._

import scalaz._, Scalaz._

import InFieldPoints._
import Points._

class InFieldPointTest extends Specification { def is =

  "InFieldPointケースクラスのテスト"                        ^
    "InFieldPointShowのテスト"                              ^
      "(1, 2)"                                              ! FieldMock().e1^
                                                            p^
    "InFieldPointEqualのテスト"                             ^
      "(1, 2) === (1, 2)"                                   ! FieldMock().e2^
      "(1, 2) !== (1, 3)"                                   ! FieldMock().e3^
      "(1, 2) !== (0, 2)"                                   ! FieldMock().e4^
                                                            p^
    "checkのテスト - Fieldはwidth=6, height=14"             ^
      "(0, 0) is in"                                        ! FieldMock().e5^
      "(5, 0) is in"                                        ! FieldMock().e6^
      "(5, 13) is in"                                       ! FieldMock().e7^
      "(0, 13) is in"                                       ! FieldMock().e8^
      "(3, -1) is out"                                      ! FieldMock().e9^
      "(6, 5) is out"                                       ! FieldMock().e10^
      "(3, 14) is out"                                      ! FieldMock().e11^
      "(-1, 5) is out"                                      ! FieldMock().e12^
                                                            p^
    "unapplyのテスト - Fieldはwidth=6, height=14"           ^
      "InFieldPoint(0, 0).unapply === Point(0, 0)"          ! FieldMock().e13^
                                                            end

  case class FieldMock() extends Mockito {

    implicit val f = mock[Field]
    f.width returns 6
    f.height returns 14

    def e1 = Point(1, 2).in must beSome.which(_.shows must_== "(1, 2)")

    def e2 = (for {
      p1 <- Point(1, 2).in
      p2 <- Point(1, 2).in
    } yield (p1 ≟ p2)) must beSome.which(_ must beTrue)

    def e3 = (for {
      p1 <- Point(1, 2).in
      p2 <- Point(1, 3).in
    } yield (p1 ≟ p2)) must beSome.which(_ must beFalse)

    def e4 = (for {
      p1 <- Point(1, 2).in
      p2 <- Point(0, 2).in
    } yield (p1 ≟ p2)) must beSome.which(_ must beFalse)

    def e5 = InFieldPoint.check(Point(0, 0), f) must beSome

    def e6 = InFieldPoint.check(Point(5, 0), f) must beSome

    def e7 = InFieldPoint.check(Point(5, 13), f) must beSome

    def e8 = InFieldPoint.check(Point(0, 13), f) must beSome

    def e9 = InFieldPoint.check(Point(3, -1), f) must beNone

    def e10 = InFieldPoint.check(Point(6, 5), f) must beNone

    def e11 = InFieldPoint.check(Point(3, 14), f) must beNone

    def e12 = InFieldPoint.check(Point(-1, 5), f) must beNone

    def e13 = Point(0, 0).in ∘ {
      case InFieldPoint(p) => p ≟ Point(0, 0)
    } must beSome.which(_ must beTrue)
  }
}
