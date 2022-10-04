package com.tco.model;

import java.util.ArrayList;

public class King extends Piece {
  private boolean canCastle;

  public King(boolean color, Square initialPosition) {
    this.color = color;
    currentPosition = initialPosition;
    canCastle = true;
  }

  public boolean getCanCastle() {
    return canCastle;
  }

  public void setCanCastle(boolean canCastle) {
    this.canCastle = canCastle;
  }

  public ArrayList<Square> getPossibleMoves(char[][] boardState) {
    ArrayList<Square> possibleMoves = new ArrayList<>();
    //TODO implement this

    return possibleMoves;
  }
}
