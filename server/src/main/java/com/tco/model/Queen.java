package com.tco.model;

import java.util.ArrayList;

public class Queen extends Piece {
  public Queen(boolean color, Square initialPosition) {
    this.color = color;
    currentPosition = initialPosition;
  }

  public ArrayList<Square> getPossibleMoves(char[][] boardState) {
    ArrayList<Square> possibleMoves = new ArrayList<>();
    //TODO implement this

    return possibleMoves;
  }
}
