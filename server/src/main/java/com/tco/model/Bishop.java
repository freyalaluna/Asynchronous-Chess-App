package com.tco.model;

import java.util.ArrayList;

public class Bishop extends Piece {
  public Bishop(boolean color, Square initialPosition) {
    this.color = color;
    currentPosition = initialPosition;
  }

  public ArrayList<Square> getPossibleMoves(char[][] boardState) {
    ArrayList<Square> possibleMoves = new ArrayList<>();
    int[][] movementDirections = {{-1,-1},{1,-1},{-1,1},{1,1}};
    
    for(int[] direction: movementDirections)
    {
      getPossibleMovesInDirection(possibleMoves,direction[0],direction[1],boardState);
    }

    return possibleMoves;
  }

  public void getPossibleMovesInDirection(ArrayList<Square> possibleMoves, int xDirection, int yDirection, char[][]boardState){
    int currentX = currentPosition.getX();
    int currentY = currentPosition.getY();
    
    for(int i = 0; i < 8; i++){
      currentX = currentX + xDirection;
      currentY = currentY + yDirection;
      if(!isValidCoord(currentX,currentY)) {break;}
      else if(boardState[currentX][currentY] != 'o'){
        if(Character.isLowerCase(boardState[currentX][currentY])== color){
        }
        else{
          possibleMoves.add(new Square(currentX,currentY));
        }
        break;
      } else {
        possibleMoves.add(new Square(currentX,currentY));
      }   
    }
}

  private boolean isValidCoord(int x, int y) {
    if (x>=0 && x<8 && y>=0 && y<8) return true;
    return false;
  }

}

