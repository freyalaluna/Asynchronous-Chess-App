package com.tco.model;

import java.util.ArrayList;

public class Pawn extends Piece {
  private boolean canDoubleMove;

  public Pawn(boolean color, Square initialPosition) {
    this.color = color;
    currentPosition = initialPosition;
    canDoubleMove = true;
  }

  public boolean getCanDoubleMove() {
    return canDoubleMove;
  }

  public void setCanDoubleMove(boolean canDoubleMove) {
    this.canDoubleMove = canDoubleMove;
  }

  private ArrayList<Square> getPossibleMoves(char[][] boardState){
    ArrayList<Square> possibleMoves = new ArrayList<>();
    int currentX = currentPosition.getX();
    int currentY = currentPosition.getY();
    int moveDirection;
    if(this.color){
      moveDirection = -1;
    } else {
      moveDirection = 1;
    }

    //Forward One Space
    if(isValidCoord(currentX, currentY+moveDirection) && boardState[currentX][currentY+moveDirection] == 'o'){
      possibleMoves.add(new Square(currentX, currentY+moveDirection));
    }

    //Forward Two Spaces
    if(this.canDoubleMove && boardState[currentX][currentY+(2*moveDirection)] == 'o'){
      possibleMoves.add(new Square(currentX, currentY+(2*moveDirection)));
    }

    //Diagonals
    boolean leftCapture = isValidCoord(currentX-1, currentY+moveDirection) && 
                          isEnemyPiece(boardState, currentX, currentY, -1, moveDirection, this.color);
    boolean rightCapture = isValidCoord(currentX+1, currentY-1) && 
                          isEnemyPiece(boardState, currentX, currentY, 1, moveDirection, this.color);

    if(leftCapture){
      possibleMoves.add(new Square(currentX-1, currentY+moveDirection));
    }

    if(rightCapture){
      possibleMoves.add(new Square(currentX+1, currentY+moveDirection));
    }

    //En Passants
    //fturner: TODO: This is gross. Clean it up, if possible
    if(isValidCoord(currentX-1, currentY+moveDirection) && boardState[currentX-1][currentY] == 'p'){
      Character adjacentPawn = boardState[currentX-1][currentY];
      if(adjacentPawn.toLowerCase() == 'p' && adjacentPawn.isUpperCase() != this.color){
        if(isValidCoord(currentX-1, currentY+(3*moveDirection))){
          Character previousLoc = boardState[currentX-1][currentY+(3*moveDirection)]
          if(previousLoc.toLowerCase() == 'd' && previousLoc.isUpperCase() != this.color){
            possibleMoves.add(new Square(currentX-1, currentY+moveDirection));
          }
        }
      }
    }

    if(isValidCoord(currentX+1, currentY+moveDirection) && boardState[currentX+1][currentY] == 'p'){
      Character adjacentPawn = boardState[currentX+1][currentY];
      if(adjacentPawn.toLowerCase() == 'p' && adjacentPawn.isUpperCase() != this.color){
        if(isValidCoord(currentX+1, currentY+(3*moveDirection))){
          Character previousLoc = boardState[currentX+1][currentY+(3*moveDirection)]
          if(previousLoc.toLowerCase() == 'd' && previousLoc.isUpperCase() != this.color){
            possibleMoves.add(new Square(currentX+1, currentY+moveDirection));
          }
        }
      }
    }
    return possibleMoves;
  }

  //Maybe make this a prototype in Piece?
  private boolean isValidCoord(int x, int y){
    if((x >= 0 && x <= 7) && (y >= 0 && y <= 7)){
      return true;
    }
    return false;
  }

  private boolean isEnemyPiece(char[][] boardState, int x, int y, int xOffset, int yOffset, boolean color){
    if(boardState[x+xOffset][y+yOffset] != 'o'){
      return Character.isUpperCase(boardState[x+xOffset][y+yOffset]) != color;
    }
    return false;
  }
}
