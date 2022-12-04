package com.tco.model;

import java.util.ArrayList;

public class Square {
  private int x;
  private int y;

  public Square(int xCoord, int yCoord) {
    x = xCoord;
    y = yCoord;
  }

  public Square(String chessNotationString) {
    //a-h maps to 0-7
    int xCoord = ((int) chessNotationString.charAt(0)) - 97;
    //8-1 maps to 0-7
    int yCoord = 8 - Character.getNumericValue(chessNotationString.charAt(1));

    x = xCoord;
    y = yCoord;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Square)) return false;
    Square s = (Square) o;
    return (s.getX() == x && s.getY() == y);
  }

  @Override
  public String toString() {
    return "[" + x + ", " + y + "]";
  }
}
