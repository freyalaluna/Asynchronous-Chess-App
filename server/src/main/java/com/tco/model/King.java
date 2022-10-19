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
    int[][] movePatterns = {
      {1, 1},  //Move down 1, right 1
      {0, 1},  //Move down 1
      {-1, 1}, //Move down 1, left 1
      {1, 0},  //Move right 1
      {-1, 0}, //Move left 1
      {1, -1}, //Move up 1, right 1
      {0, -1}, //Move up 1
      {-1, -1} //Move up 1, left 1
    };
    
    ArrayList<Square> possibleMoves = new ArrayList<>();
    int currentX = currentPosition.getX();
    int currentY = currentPosition.getY();

    //Surrounding 8 Squares
    for(int[] pattern : movePatterns){
      if(isValidMove(boardState, currentX+pattern[0], currentY+pattern[1])){
          possibleMoves.add(new Square(currentX+pattern[0], currentY+pattern[1]));
      }
    }

    //TODO: Implement Castling check
    //Both king and castling rook must not have moved, no pieces can be between the king and the rook,
    //and no "movement" that occurs during the swap can put the king in check

    return possibleMoves;
  }

  private boolean isValidMove(char[][] boardState, int x, int y){
    if((x < 0 || x > 7) || (y < 0 || y > 7)){
      return false;
    }
    if(boardState[x][y] != 'o'){
      if(Character.isUpperCase(boardState[x][y]) == this.color){
        return false;
      }
    }
    return true;
  }
}
