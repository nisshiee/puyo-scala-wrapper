package org.nisshiee.puyo.core

import org.specs2._

import Storage.PuyoType

import scalaz._, Scalaz._
import Puyos._

class PuyoTest extends Specification { def is =
  "Puyoケースクラスのテスト"                                ^
    "puyoSJのテスト"                                        ^
      "Blue -> BLUE_PUYO" ! (Puyo.puyoSJ(Blue) must_== PuyoType.BLUE_PUYO) ^
      "Red -> RED_PUYO" ! (Puyo.puyoSJ(Red) must_== PuyoType.RED_PUYO) ^
      "Green -> GREEN_PUYO" ! (Puyo.puyoSJ(Green) must_== PuyoType.GREEN_PUYO) ^
      "Yellow -> YELLOW_PUYO" ! (Puyo.puyoSJ(Yellow) must_== PuyoType.YELLOW_PUYO) ^
      "Purple -> PURPLE_PUYO" ! (Puyo.puyoSJ(Purple) must_== PuyoType.PURPLE_PUYO) ^
      "Ojama -> OJAMA_PUYO" ! (Puyo.puyoSJ(Ojama) must_== PuyoType.OJAMA_PUYO) ^
                                                            p^
    "puyoJSのテスト"                                        ^
      "BLUE_PUYO -> Blue" ! (Puyo.puyoJS(PuyoType.BLUE_PUYO) must_== Blue) ^
      "RED_PUYO -> Red" ! (Puyo.puyoJS(PuyoType.RED_PUYO) must_== Red) ^
      "GREEN_PUYO -> Green" ! (Puyo.puyoJS(PuyoType.GREEN_PUYO) must_== Green) ^
      "YELLOW_PUYO -> Yellow" ! (Puyo.puyoJS(PuyoType.YELLOW_PUYO) must_== Yellow) ^
      "PURPLE_PUYO -> Purple" ! (Puyo.puyoJS(PuyoType.PURPLE_PUYO) must_== Purple) ^
      "OJAMA_PUYO -> Ojama" ! (Puyo.puyoJS(PuyoType.OJAMA_PUYO) must_== Ojama) ^
                                                            p^
    "colorsのテスト"                                        ^
      "contains Blue" ! (Puyo.colors contains Blue must beTrue) ^
      "contains Red" ! (Puyo.colors contains Red must beTrue) ^
      "contains Green" ! (Puyo.colors contains Green must beTrue) ^
      "contains Yellow" ! (Puyo.colors contains Yellow must beTrue) ^
      "contains Purple" ! (Puyo.colors contains Purple must beTrue) ^
      "not contains Ojama" ! (Puyo.colors contains Ojama must beFalse) ^
                                                            p^
    "PuyoShowのテスト"                                      ^
      "Blue -> 青" ! ((Blue: Puyo).shows must_== "青") ^
      "Red -> 赤" ! ((Red: Puyo).shows must_== "赤") ^
      "Green -> 緑" ! ((Green: Puyo).shows must_== "緑") ^
      "Yellow -> 黄" ! ((Yellow: Puyo).shows must_== "黄") ^
      "Purple -> 紫" ! ((Purple: Puyo).shows must_== "紫") ^
      "Ojama -> ○" ! ((Ojama: Puyo).shows must_== "○") ^
                                                            p^
    "PuyoEqualsのテスト"                                    ^
      "Blue === Blue" ! (PuyoEqual.equal(Blue, Blue) must beTrue) ^
      "Blue !== Red" ! (PuyoEqual.equal(Blue, Red) must beFalse) ^
                                                            end
}
