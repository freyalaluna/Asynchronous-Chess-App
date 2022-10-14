package com.tco.model;

import java.util.ArrayList;

public class Knight extends Piece {
  public Knight(boolean color, Square initialPosition) {
    this.color = color;
    currentPosition = initialPosition;
  }

  public ArrayList<Square> getPossibleMoves(char[][] boardState) {
    int[][] movementPatterns = {
      {2, -1},  //move 2 right, 1 up
      {2, 1},   //move 2 right, 1 down
      {1, 2},   //move 1 right, 2 down
      {-1, 2},  //move 1 left, 2 down
      {-2, 1},  //move 2 left, 1 down
      {-2, -1}, //move 2 left, 1 up
      {-1, -2}, //move 1 left, 2 up
      {1, -2},  //move 1 right, 2 up
    };

    ArrayList<Square> possibleMoves = new ArrayList<>();

    int newX, newY;
    for (int[] pattern : movementPatterns) {
      newX = currentPosition.getX() + pattern[0];
      newY = currentPosition.getY() + pattern[1];

      if ((newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)) {
        if (boardState[newY][newX] != 'o') {
          if (Character.isUpperCase(boardState[newY][newX]) == color) {
            possibleMoves.add(new Square(newX, newY));
          }
        } else {
          possibleMoves.add(new Square(newX, newY));
        }
      }
    }

    return possibleMoves;
  }
}
