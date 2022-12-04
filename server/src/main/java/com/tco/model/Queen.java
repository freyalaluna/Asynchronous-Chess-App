package com.tco.model;

import java.util.ArrayList;

public class Queen extends Piece {
  public Queen(boolean color, Square initialPosition) {
    this.color = color;
    currentPosition = initialPosition;
  }

  public ArrayList<Square> getPossibleMoves(char[][] boardState) {
    ArrayList<Square> possibleMoves = new ArrayList<>();
    for (int i=-1; i<2; ++i) {
      for (int j=-1; j<2; ++j) {
        if (i==0 && j==0) continue;
        possibleMoves.addAll(getMovesInDirection(boardState, i, j));
      }
    }

    return possibleMoves;
  }

  private ArrayList<Square> getMovesInDirection(char[][] boardState, int xDirection, int yDirection) {
    ArrayList<Square> directionMoves = new ArrayList<>();
    int currentX = currentPosition.getX();
    int currentY = currentPosition.getY();
    int deltaX, deltaY;
    int targetX, targetY;
    for (int i=1; i<8; ++i) {
      deltaX = i*xDirection;
      deltaY = i*yDirection;
      targetX = currentX+deltaX;
      targetY = currentY+deltaY;
      if (!isValidCoord(targetX, targetY)) {break;}
      else if (boardState[targetY][targetX]!='o') {
        if (Character.isLowerCase(boardState[targetY][targetX])==color) {
        }
        else {
          directionMoves.add(new Square(targetX, targetY));
        }
        break;
      } else {
        directionMoves.add(new Square(targetX, targetY));
      }
    }
    return directionMoves;
  }

  private boolean isValidCoord(int x, int y) {
    if (0<=x && x<8 && 0<=y && y<8) return true;
    return false;
  }
}
