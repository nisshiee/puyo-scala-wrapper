package org.nisshiee.puyo.core

import scalaz._, Scalaz._

import jp.ac.nagoya_u.is.ss.kishii.usui.system.game.{ AbstractPlayer => JPlayer }

abstract class Player[A](name: String) extends JPlayer(name) {

  def init: A

  def action(
    myBoard: Board,
    enemyBoard: Board,
    state: A
  ): (Action, A)

  var state: A = _

  override def initialize = { state = init }

  override def doMyTurn = {
    val myBoard = new JBoardWrapper(getMyBoard) |> Board.boardJS
    val enemyBoard = new JBoardWrapper(getEnemyBoard) |> Board.boardJS

    val (a, s) = action(myBoard, enemyBoard, state)
    state = s
    Action.actionSJ(a)
  }

  override def inputResult = ()
}
