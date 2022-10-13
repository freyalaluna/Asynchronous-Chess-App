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
    int currentX = this.getX();
    int currentY = this.getY();

    //Surrounding 8 Squares
    for(int row = -1; row <= 1; row++){
      for(int col = -1; col <=1; col++){
        if(isValidMove(currentX+col, currentY+row) && boardState[currentX+col][currentY+row] == 'o'){
          possibleMoves.add(new Square(currentX+col, currentY+row));
        }
      }
    }

    //Castling
    return possibleMoves;
  }

  private boolean isValidMove(int x, int y){
    if(this.getX() == x && this.getY() == y){
      return false;
    }
    if((x < 0 || x > 7) || (y < 0 || y > 7)){
      return false;
    }
    return true;
  }
}
