package com.tco.model;

import java.util.ArrayList;

public class Rook extends Piece {
  public Rook(boolean color, Square initialPosition) {
    this.color = color;
    currentPosition = initialPosition;
  }

  public ArrayList<Square> getPossibleMoves(char[][] boardState) {
    int currentRow = currentPosition.getX();
    int currentColumn = currentPosition.getY();
    ArrayList<Square> possibleMoves = new ArrayList<>();
    ArrayList<Square> movesToTheRight = new ArrayList<>();
    ArrayList<Square> movesToTheLeft = new ArrayList<>();
    ArrayList<Square> movesUp = new ArrayList<>();
    ArrayList<Square> movesDown = new ArrayList<>();
    movesToTheRight = getMovesToRight(currentRow, currentColumn, boardState);
    movesToTheLeft = getMovesToLeft(currentRow, currentColumn, boardState);
    movesUp = getMovesGoingUp(currentRow, currentColumn, boardState);
    movesDown = getMovesGoingDown(currentRow, currentColumn, boardState);
    for (int i = 0; i < movesToTheRight.size();i++){
      possibleMoves.add(movesToTheRight.get(i));
    }
    for (int i = 0; i < movesToTheLeft.size();i++){
      possibleMoves.add(movesToTheLeft.get(i));
    }
    for (int i = 0; i < movesUp.size();i++){
      possibleMoves.add(movesUp.get(i));
    }
    for (int i = 0; i < movesDown.size();i++){
      possibleMoves.add(movesDown.get(i));
    }


    return possibleMoves;
  }

  public ArrayList<Square> getMovesToRight(int currentRow, int currentColumn, char[][] boardState) {
    ArrayList<Square> getRightDirectionMoves = new ArrayList<>();
    for(int i = 0; i < 8; i++){
      currentColumn++;
      if(!isValidCoord(currentRow,currentColumn)) {
        break;
      }
      else if(boardState[currentRow][currentColumn] != 'o') {
        if(Character.isLowerCase(boardState[currentRow][currentColumn])!= color) {
        }
        else {
          getRightDirectionMoves.add(new Square(currentRow,currentColumn));
        }
        break;
      } else {
        getRightDirectionMoves.add(new Square(currentRow,currentColumn));
      }   
  }
  return getRightDirectionMoves;
}

  public ArrayList<Square> getMovesToLeft(int currentRow, int currentColumn, char[][] boardState) {
    ArrayList<Square> getLeftDirectionMoves = new ArrayList<>();
    for(int i = 0; i < 8; i++){
      currentColumn--;
      if(!isValidCoord(currentRow,currentColumn)) {
        break;
      }
      else if(boardState[currentRow][currentColumn] != 'o') {
        if(Character.isLowerCase(boardState[currentRow][currentColumn])!= color) {
        }
        else {
          getLeftDirectionMoves.add(new Square(currentRow,currentColumn));
        }
        break;
      } else {
        getLeftDirectionMoves.add(new Square(currentRow,currentColumn));
      }   
  }
  return getLeftDirectionMoves;
  }

  public ArrayList<Square> getMovesGoingUp(int currentRow, int currentColumn, char[][] boardState) {
    ArrayList<Square> getUpDirectionMoves = new ArrayList<>();
    for(int i = 0; i < 8; i++){
      currentRow++;
      if(!isValidCoord(currentRow,currentColumn)) {
        break;
      }
      else if(boardState[currentRow][currentColumn] != 'o') {
        if(Character.isLowerCase(boardState[currentRow][currentColumn])!= color) {
        }
        else {
          getUpDirectionMoves.add(new Square(currentRow,currentColumn));
        }
        break;
      } else {
        getUpDirectionMoves.add(new Square(currentRow,currentColumn));
      }   
  }
  return getUpDirectionMoves;
  }
  public ArrayList<Square> getMovesGoingDown(int currentRow, int currentColumn, char[][] boardState) {
    ArrayList<Square> getDownDirectionMoves = new ArrayList<>();
    for(int i = 0; i < 8; i++){
      currentRow--;
      if(!isValidCoord(currentRow,currentColumn)) {
        break;
      }
      //if space is not an empty space
      else if(boardState[currentRow][currentColumn] != 'o') {
        //if char is white(uppercase) and if white is the color of the player
        if(Character.isLowerCase(boardState[currentRow][currentColumn])!=color) {
        }
        //if the colors don't match 
        else {
          getDownDirectionMoves.add(new Square(currentRow,currentColumn));
        }
        break;
      } else {
        getDownDirectionMoves.add(new Square(currentRow,currentColumn));
      }   
  }
  return getDownDirectionMoves;
  }
  private boolean isValidCoord(int x, int y) {
    if (x>=0 && x<8 && y>=0 && y<8) return true;
    return false;
  }
}
