package com.tco.model;

import java.util.ArrayList;
import java.util.HashMap;

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

  public boolean[] validateIncomingMove(String sourceSquare, String targetSquare, String piece, String fenString) {
    Square sourceSquareObj = new Square(sourceSquare);
    Square targetSquareObj = new Square(targetSquare);
    PieceFactory factory = new PieceFactory();
    Piece pieceObj = factory.createPieceFENType(piece.charAt(1), piece.charAt(0) == 'b', sourceSquareObj);
    boolean isGameOver = setBoardStateFromFEN(fenString, targetSquareObj);
    boolean isLegalMove = isValidMove(pieceObj, targetSquareObj);
    return new boolean[] {isLegalMove, isGameOver};
  }

  private boolean setBoardStateFromFEN(String fenString, Square targetSquare) {
    //list of number of pieces
    HashMap<Character, Integer> pieceCounter = new HashMap<>();
    //get only piece information from fenString
    String trimmedFENString = fenString.substring(0, fenString.indexOf(" "));

    int x = 0;
    int y = 0;

    for (int i = 0; i < trimmedFENString.length(); i++) {
      if (trimmedFENString.charAt(i) == '/') { //reached end of row
        x = 0;
        y++;
      }
      else if (Character.isDigit(trimmedFENString.charAt(i))) { //encounter empty square(s)
        int numEmptySquares = Character.getNumericValue(trimmedFENString.charAt(i));
        for (int j = 0; j < numEmptySquares; j++) {
          this.boardState[y][x] = 'o';
          x++;
        }
      }
      else { //encountered piece
        char piece = trimmedFENString.charAt(i);
        pieceCounter.put(piece, pieceCounter.getOrDefault(piece, 0) + 1);
        this.boardState[y][x] = piece;
        x++;
      }

    }

    char targetPiece = this.boardState[targetSquare.getY()][targetSquare.getX()];
    
    if (targetPiece != 'o') { //game can only be over on a capture move
      return (pieceCounter.size() < 12  || pieceCounter.get(targetPiece) <= 1);
    }
    return false;
  }

  private boolean isValidMove(Piece piece, Square targetSquare) {
    ArrayList<Square> validMoves = piece.getPossibleMoves(this.boardState);
    return validMoves.contains(targetSquare);
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

  public char[][] getBoardState() {
    return boardState;
  }

  public void updateBoardState(int x, int y, char piece) {
    boardState[y][x] = piece;
  }
}
