package org.nisshiee.puyo.core

import org.specs2._, mock._

import scalaz._, Scalaz._

import jp.ac.nagoya_u.is.ss.kishii.usui.system.game.{ Field => JField }
import jp.ac.nagoya_u.is.ss.kishii.usui.system.storage.PuyoType

class FieldTest extends Specification { def is =

  "Fieldクラスのテスト"                                     ^
    "fieldJSのテスト"                                       ^
      "widthがコピーされる"                                 ! JMock().e1^
      "heightがコピーされる"                                ! JMock().e2^
      "deadLineがコピーされる"                              ! JMock().e3^
                                                            p^
    "applyのテスト"                                         ^
      "ぷよが存在する場合Some[Puyo]が返る"                  ! JMock().e4^
      "ぷよが存在しない場合Noneが返る"                      ! JMock().e5^
                                                            p^
                                                            end

  case class JMock() extends Mockito {
    val jw = mock[JFieldWrapper]
    jw.deadLine returns 12
    jw.height returns 14
    jw.width returns 6
    jw.puyoType(0, 0) returns PuyoType.RED_PUYO
    jw.puyoType(0, 10) returns null

    implicit val f = Field.fieldJS(jw)

    def e1 = f.width must_== 6

    def e2 = f.height must_== 14

    def e3 = f.deadLine must_== 12

    def e4 = Point(0, 0).in ∘ (f.apply _) must beSome.which(_ must_== Some(Red))

    def e5 = Point(0, 10).in ∘ (f.apply _) must beSome.which(_ must beNone)
  }
}
