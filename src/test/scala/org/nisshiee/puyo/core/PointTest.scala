package org.nisshiee.puyo.core

import org.specs2._, mock._

import scalaz._, Scalaz._, Puyoz._

class PointTest extends Specification { def is =

  "Pointケースクラスのテスト"                               ^
    "PointShowのテスト"                                     ^
      "(1, 2)" ! (Point(1, 2).shows must_== "(1, 2)") ^
                                                            p^
    "PointEqualのテスト"                                    ^
      "(1, 2) === (1, 2)"                                   ! e1^
      "(1, 2) !== (1, 3)"                                   ! e2^
      "(1, 2) !== (0, 2)"                                   ! e3^
                                                            p^
    "PointSemigroupのテスト"                                ^
      "(1, 2) |+| (3, 4) === (4, 6)"                        ! e4^
                                                            p^
    "PointZeroのテスト"                                     ^
      "zero === (0, 0)" ! (PointZero.zero must_== Point(0, 0)) ^
                                                            p^
    "基本演算のテスト"                                      ^
      "(1, 2) + (3, 4) === (4, 6)"                          ! e5^
      "(3, 4) - (1, 2) === (2, 2)"                          ! e6^
      "-(1, 2) === (-1, -2)" ! (-Point(1, 2) must_== Point(-1, -2)) ^
                                                            p^
    "inのテスト - Fieldはwidth=6, height=14"                ^
      "(0, 0) is in"                                        ! FieldMock().e7^
      "(5, 0) is in"                                        ! FieldMock().e8^
      "(5, 13) is in"                                       ! FieldMock().e9^
      "(0, 13) is in"                                       ! FieldMock().e10^
      "(3, -1) is out"                                      ! FieldMock().e11^
      "(6, 5) is out"                                       ! FieldMock().e12^
      "(3, 14) is out"                                      ! FieldMock().e13^
      "(-1, 5) is out"                                      ! FieldMock().e14^
                                                            end

  def e1 = PointEqual.equal(
    Point(1, 2),
    Point(1, 2)
  ) must beTrue

  def e2 = PointEqual.equal(
    Point(1, 2),
    Point(1, 3)
  ) must beFalse

  def e3 = PointEqual.equal(
    Point(1, 2),
    Point(0, 2)
  ) must beFalse

  def e4 = Point(1, 2) |+| Point(3, 4) must_== Point(4, 6)

  def e5 = Point(1, 2) + Point(3, 4) must_== Point(4, 6)

  def e6 = Point(3, 4) - Point(1, 2) must_== Point(2, 2)

  case class FieldMock() extends Mockito {

    implicit val f = mock[Field]
    f.width returns 6
    f.height returns 14

    def e7 = Point(0, 0).in must beSome

    def e8 = Point(5, 0).in must beSome

    def e9 = Point(5, 13).in must beSome

    def e10 = Point(0, 13).in must beSome

    def e11 = Point(3, -1).in must beNone

    def e12 = Point(6, 5).in must beNone

    def e13 = Point(3, 14).in must beNone

    def e14 = Point(-1, 5).in must beNone
  }
}
