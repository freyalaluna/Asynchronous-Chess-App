package com.tco.requests;

import java.util.ArrayList;
import java.util.Arrays;
import com.tco.model.Board;
import com.tco.model.Square;
import com.tco.model.Piece;
import com.tco.model.PieceFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveRequest extends Request {
  private static final transient Logger log = LoggerFactory.getLogger(MoveRequest.class);
  
  private String sourceSquare;
  private String targetSquare;
  private String piece;
  private String gameState;
  private boolean isLegalMove;

  @Override
  public void buildResponse() {
    Square initialPos = transformSquare(sourceSquare);
    Square targetPos = transformSquare(targetSquare);
    Piece targetPiece = transformPiece(initialPos);
    char[][] boardState = transformGameState(gameState);
    validateMove(targetPiece, targetPos, boardState);
  }

  public void validateMove(Piece piece, Square target, char[][] boardState) {
    ArrayList<Square> validMoves = piece.getPossibleMoves(boardState);
    this.isLegalMove = validMoves.contains(target);
  }

  public Square transformSquare(String posString) {
    //a-h maps to 0-7
    int x = ((int) posString.charAt(0)) - 97;
    //8-1 maps to 0-7
    int y = 8 - Character.getNumericValue(posString.charAt(1));

    return new Square(x, y);
  }

  public Piece transformPiece(Square initialPos) {
    char identifier = piece.charAt(1);
    char color = piece.charAt(0);

    PieceFactory factory = new PieceFactory();
    Piece targetPiece = factory.createPieceFENType(identifier, color == 'b', initialPos);

    return targetPiece;
  }

  public char[][] transformGameState(String fenString) {
    //get only piece information from fenString
    String trimmedFENString = fenString.substring(0, fenString.indexOf(" "));

    char[][] boardState = new char[8][8];
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
          boardState[y][x] = 'o';
          x++;
        }
      }
      else { //encountered piece
        boardState[y][x] = trimmedFENString.charAt(i);
        x++;
      }

    }

    return boardState;
  }


  //VISIBLE FOR TESTING
  public void setSourceSquare(String source) {
    this.sourceSquare = source;
  }

  public void setTargetSquare(String target) {
    this.targetSquare = target;
  }

  public void setPiece(String p) {
    this.piece = p;
  }

  public void setGameState(String fen) {
    this.gameState = fen;
  }

  public boolean getIsLegalMove() {
    return this.isLegalMove;
  }
}
