package com.tco.requests;

import java.util.ArrayList;
import com.tco.model.Board;
import com.tco.model.Square;
import com.tco.model.Piece;
import com.tco.model.Pawn;
import com.tco.model.Knight;
import com.tco.model.Bishop;
import com.tco.model.Rook;
import com.tco.model.Queen;
import com.tco.model.King;

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
    validateMove(targetPiece, initialPos, boardState);
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
    Piece targetPiece;

    switch (identifier) {
      case 'P':
        if (color == 'w') {
          targetPiece = new Pawn(false, initialPos);
        } else {
          targetPiece = new Pawn(true, initialPos);
        }
        break;
      case 'N':
        if (color == 'w') {
          targetPiece = new Knight(false, initialPos);
        } else {
          targetPiece = new Knight(true, initialPos);
        }
        break;
      case 'B':
        if (color == 'w') {
          targetPiece = new Bishop(false, initialPos);
        } else {
          targetPiece = new Bishop(true, initialPos);
        }
        break;
      case 'R':
        if (color == 'w') {
          targetPiece = new Rook(false, initialPos);
        } else {
          targetPiece = new Rook(true, initialPos);
        }
        break;
      case 'Q':
        if (color == 'w') {
          targetPiece = new Queen(false, initialPos);
        } else {
          targetPiece = new Queen(true, initialPos);
        }
        break;
      default:
        if (color == 'w') {
          targetPiece = new King(false, initialPos);
        } else {
          targetPiece = new King(true, initialPos);
        }
        break;
    }

    return targetPiece;
  }

  public char[][] transformGameState(String fenString) {
    //this check may or may not be needed depending on client-side implementation
    if (fenString.equals("start")) {
      //returns initial board state
      Board board = new Board();
      char[][] boardState = board.getBoardState();
      return boardState;
    }

    char[][] boardState = new char[8][8];
    int x = 0;
    int y = 0;

    for (int i = 0; i < fenString.length(); i++) {
      if (fenString.charAt(i) == '/') { //reached end of row
        x = 0;
        y++;
      }
      else if (Character.isDigit(fenString.charAt(i))) { //encounter empty square(s)
        int numEmptySquares = Character.getNumericValue(fenString.charAt(i));
        for (int j = 0; j < numEmptySquares; j++) {
          boardState[y][x] = 'o';
          x++;
        }
      }
      else { //encountered piece
        boardState[y][x] = fenString.charAt(i);
        x++;
      }

    }

    return boardState;
  }


  //VISIBLE FOR TESTING
  public void setPiece(String p) {
    this.piece = p;
  }

  public boolean getIsLegalMove() {
    return this.isLegalMove;
  }
}
