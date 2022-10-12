package com.tco.model;

import java.util.ArrayList;

public class Rook extends Piece {
  public Rook(boolean color, Square initialPosition) {
    this.color = color;
    currentPosition = initialPosition;
  }

  public ArrayList<Square> getPossibleMoves(char[][] boardState) {
    ArrayList<Square> possibleMoves = new ArrayList<>();
    possibleMoves.add(getMovesToRight(currentPosition, boardState));
    return possibleMoves;
  }
  public ArrayList<Square> getMovesToRight(Square initialPosition, char[][] boardState) {
    ArrayList<Square> getRightDirectionMoves = new ArrayList<>();
    int currentRow = initialPosition.getX();
    int currentColumn = initialPosition.getY();
    while(isValidCoord(currentRow, currentColumn)){
      currentColumn++;
      if (Character.isUpperCase(boardState[currentRow][currentColumn])==color) {
        currentColumn++;
        if(!isValidCoord(currentRow, currentColumn)){
          break;
        }
        else{
          getRightDirectionMoves.add(new Square(currentRow, currentColumn));
        }
      }
    return getRightDirectionMoves;

  }

  private boolean isValidCoord(int x, int y) {
    if (x>=0 && x<8 && y>=0 && y<8) return true;
    return false;
  }
}
