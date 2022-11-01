package com.tco.requests;

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
    //todo implement
    this.isLegalMove = false;
  }

  public Square transformSquare(String posString) {
    //todo implement
    return new Square(-1, -1);
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
    //todo implement
    if (fenString.equals("start")) {
    }

    return new char[8][8];
  }
}
