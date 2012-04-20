package org.nisshiee.puyo.core

import org.specs2._

import scalaz._, Scalaz._

import PuyoBlocks._

class PuyoBlockTest extends Specification { def is =

  "PuyoBlockケースクラスのテスト"                           ^
    "PuyoBlockShowのテスト"                                 ^
      "(青赤)" ! (PuyoBlock(Blue, Red).shows must_== "(青赤)") ^
      "(赤緑)" ! (PuyoBlock(Red, Green).shows must_== "(赤緑)") ^
      "(青青)" ! (PuyoBlock(Blue, Blue).shows must_== "(青青)") ^
                                                            p^
    "PuyoBlockEqualのテスト"                                ^
      "(青赤) === (青赤)"                                   ! e1^
      "(青赤) !== (青緑)"                                   ! e2^
      "(青赤) !== (緑赤)"                                   ! e3^
      "(青赤) !== (黄緑)"                                   ! e4^
      "(青青) === (青青)"                                   ! e5^
                                                            end

  def e1 = PuyoBlockEqual.equal(
    PuyoBlock(Blue, Red),
    PuyoBlock(Blue, Red)
  ) must beTrue

  def e2 = PuyoBlockEqual.equal(
    PuyoBlock(Blue, Red),
    PuyoBlock(Blue, Green)
  ) must beFalse

  def e3 = PuyoBlockEqual.equal(
    PuyoBlock(Blue, Red),
    PuyoBlock(Green, Red)
  ) must beFalse

  def e4 = PuyoBlockEqual.equal(
    PuyoBlock(Blue, Red),
    PuyoBlock(Yellow, Green)
  ) must beFalse

  def e5 = PuyoBlockEqual.equal(
    PuyoBlock(Blue, Blue),
    PuyoBlock(Blue, Blue)
  ) must beTrue
}
