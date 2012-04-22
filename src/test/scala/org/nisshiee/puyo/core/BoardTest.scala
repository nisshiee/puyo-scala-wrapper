package org.nisshiee.puyo.core

import scalaz._, Scalaz._, Puyoz._

import org.specs2._, mock._

import jp.ac.nagoya_u.is.ss.kishii.usui.system.storage.PuyoType

class BoardTest extends Specification { def is =

  "BoradTestクラスのテスト"                                 ^
    "boardJSのテスト"                                       ^
      "currentがコピーされること"                           ! JMock().e1^
      "nextがコピーされること"                              ! JMock().e2^
      "nextNextがコピーされること"                          ! JMock().e3^
      "ojamaQueueがコピーされること"                        ! JMock().e4^
                                                            end

  case class JMock() extends Mockito {

    val currentw = mock[JPuyoBlockWrapper]
    currentw.base returns PuyoType.RED_PUYO
    currentw.sub returns PuyoType.BLUE_PUYO

    val nextw = mock[JPuyoBlockWrapper]
    nextw.base returns PuyoType.GREEN_PUYO
    nextw.sub returns PuyoType.YELLOW_PUYO

    val nextNextw = mock[JPuyoBlockWrapper]
    nextNextw.base returns PuyoType.PURPLE_PUYO
    nextNextw.sub returns PuyoType.PURPLE_PUYO

    val fw = mock[JFieldWrapper]
    fw.deadLine returns 12
    fw.height returns 14
    fw.width returns 6
    fw.puyoType(anyInt, anyInt) returns null

    val olw: java.util.List[java.lang.Integer] = new java.util.ArrayList
    olw.add(1)
    olw.add(2)
    olw.add(3)

    val bw = mock[JBoardWrapper]
    bw.currentPuyo returns currentw
    bw.nextPuyo returns nextw
    bw.nextNextPuyo returns nextNextw
    bw.field returns fw
    bw.ojamaList returns olw

    def e1 = Board.boardJS(bw).current ≟ PuyoBlock(Red, Blue) must beTrue

    def e2 = Board.boardJS(bw).next ≟ PuyoBlock(Green, Yellow) must beTrue

    def e3 = Board.boardJS(bw).nextNext ≟ PuyoBlock(Purple, Purple) must beTrue

    def e4 = Board.boardJS(bw).ojamaQueue ≟ List(1, 2, 3) must beTrue
  }
}
