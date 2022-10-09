package com.tco.model;

public class Board {
  private char[][] boardState = new char[8][8];
  private Player whitePlayer;
  private Player blackPlayer;
  private int matchID;

  public Board(Player whitePlayer, Player blackPlayer, int matchID) {
    this.whitePlayer = whitePlayer;
    this.blackPlayer = blackPlayer;
    this.matchID = matchID;
    setInitialBoardState();
  }

  public Board() {
    setInitialBoardState();
  }

  private void setInitialBoardState() {
    // K = king, Q = queen, R = rook, B = bishop, N = knight, P = pawn, o = empty space
    // Uppercase = white, lowercase = black
    // Black on top of board, white on bottom of board

    //rooks
    boardState[0][0] = 'r';
    boardState[0][7] = 'r';
    boardState[7][0] = 'R';
    boardState[7][7] = 'R';
    //knights
    boardState[0][1] = 'n';
    boardState[0][6] = 'n';
    boardState[7][1] = 'N';
    boardState[7][6] = 'N';
    //bishops
    boardState[0][2] = 'b';
    boardState[0][5] = 'b';
    boardState[7][2] = 'B';
    boardState[7][5] = 'B';
    //queens
    boardState[0][3] = 'q';
    boardState[7][3] = 'Q';
    //kings
    boardState[0][4] = 'k';
    boardState[7][4] = 'K';
    //pawns and empty rows
    for (int i = 0; i < boardState[1].length; i++) {
      boardState[1][i] = 'p';
      boardState[2][i] = 'o';
      boardState[3][i] = 'o';
      boardState[4][i] = 'o';
      boardState[5][i] = 'o';
      boardState[6][i] = 'P';
    }
  }

  public boolean validateMove(Move incomingMove) {
    boolean isValidMove = false;
    //call getPossibleMoves() on piece given current board state, see if returned ArrayList<Square> contains incomingMove.getEndCoord()
    return isValidMove;
  }

  public char[][] getBoardState() {
    return boardState;
  }

  public void updateBoardState(int x, int y, char piece) {
    boardState[y][x] = piece;
  }
}
