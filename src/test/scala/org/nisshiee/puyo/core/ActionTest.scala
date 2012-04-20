package org.nisshiee.puyo.core

import org.specs2._, mock._

import scalaz._, Scalaz._

import Actions._

class ActionTest extends Specification { def is =

  "Actionクラスのテスト"                                    ^
    "checkのテスト - Field.width = 6とする"                 ^
      "direction = Upの場合"                                ^
        "-1 => out"                                         ! JMock().e1^
        "0 => in"                                           ! JMock().e2^
        "5 => in"                                           ! JMock().e3^
        "6 => out"                                          ! JMock().e4^
                                                            p^
      "direction = Downの場合"                              ^
        "-1 => out"                                         ! JMock().e5^
        "0 => in"                                           ! JMock().e6^
        "5 => in"                                           ! JMock().e7^
        "6 => out"                                          ! JMock().e8^
                                                            p^
      "direction = Rightの場合 - 5はout"                    ^
        "-1 => out"                                         ! JMock().e9^
        "0 => in"                                           ! JMock().e10^
        "4 => in"                                           ! JMock().e11^
        "5 => out"                                          ! JMock().e12^
                                                            p^
      "direction = Leftの場合 - 0はout"                     ^
        "0 => out"                                          ! JMock().e13^
        "1 => in"                                           ! JMock().e14^
        "5 => in"                                           ! JMock().e15^
        "6 => out"                                          ! JMock().e16^
                                                            p^
    "checkに通ったらちゃんとコピーされてること - ↑3"       ^
      "directionを取得できる"                               ! JMock().e17^
      "colを取得できる"                                    ! JMock().e18^
                                                            p^
    "ActionShowのテスト"                                    ^
      "↑3"                                                 ! JMock().e19^
                                                            p^
    "ActionEqualのテスト"                                    ^
      "↑3 === ↑3"                                         ! JMock().e20^
                                                            end

  case class JMock() extends Mockito {

    val fw = mock[JFieldWrapper]
    fw.width returns 6

    implicit val f = Field.fieldJS(fw)

    def e1 = Action.check(Up, -1) must beNone
    def e2 = Action.check(Up, 0) must beSome
    def e3 = Action.check(Up, 5) must beSome
    def e4 = Action.check(Up, 6) must beNone

    def e5 = Action.check(Down, -1) must beNone
    def e6 = Action.check(Down, 0) must beSome
    def e7 = Action.check(Down, 5) must beSome
    def e8 = Action.check(Down, 6) must beNone

    def e9 = Action.check(Right, -1) must beNone
    def e10 = Action.check(Right, 0) must beSome
    def e11 = Action.check(Right, 4) must beSome
    def e12 = Action.check(Right, 5) must beNone

    def e13 = Action.check(Left, 0) must beNone
    def e14 = Action.check(Left, 1) must beSome
    def e15 = Action.check(Left, 5) must beSome
    def e16 = Action.check(Left, 6) must beNone

    def e17 = Action.check(Up, 3) must beSome.which(_.direction must_== Up)
    def e18 = Action.check(Up, 3) must beSome.which(_.col must_== 3)

    def e19 = Action.check(Up, 3) must beSome.which(_.shows must_== "↑3")

    def e20 = (for {
      a1 <- Action.check(Up, 3)
      a2 <- Action.check(Up, 3)
    } yield (a1 ≟ a2)) must_== Some(true)
  }
}
