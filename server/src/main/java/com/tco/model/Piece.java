package com.tco.model;

import java.util.ArrayList;

public abstract class Piece {
  boolean color;  // 0 for white, 1 for black
  Square currentPosition;

  public abstract ArrayList<Square> getPossibleMoves(char[][] boardState);

  public Square getColor() {
    return color;
  }

  public Square getPosition() {
    return currentPosition;
  }

  public void setPosition(Square newPos) {
    currentPosition = newPos;
  }
}
