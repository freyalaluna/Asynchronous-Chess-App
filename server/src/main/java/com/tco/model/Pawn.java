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

  public ArrayList<Square> getPossibleMoves(char[][] boardState){
    ArrayList<Square> possibleMoves = new ArrayList<>();
    int currentX = currentPosition.getX();
    int currentY = currentPosition.getY();
    int moveDirection;
    if(this.color){
      moveDirection = 1;
    } else {
      moveDirection = -1;
    }

    //Forward One Space
    if(isValidCoord(currentX, currentY+moveDirection) && boardState[currentY+moveDirection][currentX] == 'o'){
      possibleMoves.add(new Square(currentX, currentY+moveDirection));
    }

    //Forward Two Spaces
    if(this.canDoubleMove && boardState[currentY+(2*moveDirection)][currentX] == 'o' && boardState[currentY+moveDirection][currentX] == 'o'){
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

    //En Passant
    //Pawn must be exactly 3 rows forward from where they started, have an enemy pawn to either side, and
    //the enemy pawn must have double-moved the previous turn for en passant to be legal
    if((this.color == false && currentY == 3) || (this.color == true && currentY == 4)){
      if(canEnPassant(boardState, currentX, currentY, -1, moveDirection, this.color)){
        possibleMoves.add(new Square(currentX-1, currentY+moveDirection));
      }
      if(canEnPassant(boardState, currentX, currentY, 1, moveDirection, this.color)){
        possibleMoves.add(new Square(currentX+1, currentY+moveDirection));
      }
    }
    return possibleMoves;
  }

  private boolean isValidCoord(int x, int y){
    if((x >= 0 && x <= 7) && (y >= 0 && y <= 7)){
      return true;
    }
    return false;
  }

  private boolean isEnemyPiece(char[][] boardState, int x, int y, int xOffset, int yOffset, boolean color){
    if(boardState[y+yOffset][x+xOffset] != 'o'){
      return Character.isLowerCase(boardState[y+yOffset][x+xOffset]) != color;
    }
    return false;
  }

  private boolean canEnPassant(char[][] boardState, int x, int y, int xOffset, int yOffset, boolean color){
    if(!isValidCoord(x+xOffset, y+yOffset)){
      return false;
    }
    
    Character adjacentPiece = boardState[y][x+xOffset];
    if(Character.toLowerCase(adjacentPiece) != 'p' || Character.isLowerCase(adjacentPiece) == this.color){
      return false;
    }

    if(!isValidCoord(x+xOffset, y+(2*yOffset))){
      return false;
    }

    Character previousLoc = boardState[y+(2*yOffset)][x+xOffset];
    if(Character.toLowerCase(previousLoc) != 'd' || Character.isLowerCase(previousLoc) == this.color){
      return false;
    }

    return true;
  }
}
