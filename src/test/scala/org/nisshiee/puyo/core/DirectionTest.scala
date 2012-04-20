package org.nisshiee.puyo.core

import org.specs2._

import scalaz._, Scalaz._

import jp.ac.nagoya_u.is.ss.kishii.usui.system.game.Puyo.PuyoDirection

import Directions._

class DirectionTest extends Specification { def is =

  "Directionケースクラスのテスト"                           ^
    "DirectionShowのテスト"                                 ^
      "↑" ! ((Up: Direction).shows ≟ "↑" must beTrue) ^
      "↓" ! ((Down: Direction).shows ≟ "↓" must beTrue) ^
      "→" ! ((Right: Direction).shows ≟ "→" must beTrue) ^
      "←" ! ((Left: Direction).shows ≟ "←" must beTrue) ^
                                                            p^
    "DirectionEqualのテスト"                                ^
      "↑ === ↑" ! ((Up: Direction) ≟ (Up: Direction) must beTrue) ^
      "↑ !== ↓" ! ((Up: Direction) ≟ (Down: Direction) must beFalse) ^
                                                            p^
    "directionSJのテスト"                                   ^
      "↑ => UP" ! (Direction.directionSJ(Up) must_== PuyoDirection.UP) ^
      "↓ => DOWN" ! (Direction.directionSJ(Down) must_== PuyoDirection.DOWN) ^
      "→ => RIGHT" ! (Direction.directionSJ(Right) must_== PuyoDirection.RIGHT) ^
      "← => LEFT" ! (Direction.directionSJ(Left) must_== PuyoDirection.LEFT) ^
                                                            end
}
  