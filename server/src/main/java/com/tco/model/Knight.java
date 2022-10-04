package com.tco.model;

import java.util.ArrayList;

public class Knight extends Piece {
  public Knight(boolean color, Square initialPosition) {
    this.color = color;
    currentPosition = initialPosition;
  }

  public ArrayList<Square> getPossibleMoves(char[][] boardState) {
    ArrayList<Square> possibleMoves = new ArrayList<>();
    //TODO implement this

    return possibleMoves;
  }
}
