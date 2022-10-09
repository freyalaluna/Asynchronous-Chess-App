package com.tco.model;

import java.util.ArrayList;

public class Bishop extends Piece {
  public Bishop(boolean color, Square initialPosition) {
    this.color = color;
    currentPosition = initialPosition;
  }

  public ArrayList<Square> getPossibleMoves(char[][] boardState) {
    ArrayList<Square> possibleMoves = new ArrayList<>();

    int currentX = currentPosition.getX();
    int currentY = currentPosition.getY();
    // up left
    for(int i = currentPosition.getX()-1; i >= 0; i--){
      currentX--;
      currentY--;
      if(boardState[currentX][currentY] != 'o'){
        if(Character.isUpperCase(boardState[currentX][currentY])== color){
        }
        else{
          possibleMoves.add(new Square(currentX,currentY));
        }
        break;
      } else {
        possibleMoves.add(new Square(currentX,currentY));
      }   
    }

    currentX = currentPosition.getX();
    currentY = currentPosition.getY();
    // up right
    for(int i = currentPosition.getX(); i<=7 ; i++){
      currentX++;
      currentY--;
      if(boardState[currentX][currentY] != 'o'){
        if(Character.isUpperCase(boardState[currentX][currentY])== color){
        }
        else{
          possibleMoves.add(new Square(currentX,currentY));
        }
        break;
      } else {
        possibleMoves.add(new Square(currentX,currentY));
      }   
    }

    currentX = currentPosition.getX();
    currentY = currentPosition.getY();
    // down left
    for(int i = currentPosition.getY(); i <= 7; i++){
      currentY++;
      currentX--;
      if(boardState[currentX][currentY] != 'o'){
        if(Character.isUpperCase(boardState[currentX][currentY])== color){
        }
        else{
          possibleMoves.add(new Square(currentX,currentY));
        }
        break;
      } else {
        possibleMoves.add(new Square(currentX,currentY));
      }   
    }

    currentX = currentPosition.getX();
    currentY = currentPosition.getY();
    //down right
    for(int i = currentPosition.getY(); i <= 7; i++){
      currentX++;
      currentY++;
      if(boardState[currentX][currentY] != 'o'){
        if(Character.isUpperCase(boardState[currentX][currentY])== color){
        }
        else{
          possibleMoves.add(new Square(currentX,currentY));
        }
        break;
      } else {
        possibleMoves.add(new Square(currentX,currentY));
      }   
    }
    return possibleMoves;
  }

  private boolean isValidCoord(int x, int y) {
    if (x>=0 && x<8 && y>=0 && y<8) return true;
    return false;
  }
}
