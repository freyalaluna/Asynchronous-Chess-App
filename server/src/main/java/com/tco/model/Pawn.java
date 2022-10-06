package com.tco.model;

import java.util.ArrayList;

public class Pawn extends Piece {
  private boolean canDoubleMove;

  public Pawn(boolean color, Square initialPosition) {
    this.color = color;
    currentPosition = initialPosition;
    canDoubleMove = true;
  }

  public boolean getCanDoubleMove() {
    return canDoubleMove;
  }

  public void setCanDoubleMove(boolean canDoubleMove) {
    this.canDoubleMove = canDoubleMove;
  }

  public ArrayList<Square> getPossibleMoves(char[][] boardState) {
    ArrayList<Square> possibleMoves = new ArrayList<>();
    //TODO implement this

    return possibleMoves;
  }
}
