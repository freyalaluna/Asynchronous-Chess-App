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

public class MoveRequest {
  private static final transient Logger log = LoggerFactory.getLogger(MoveRequest.class);
  
  private String sourceSquare;
  private String targetSquare;
  private String piece;
  private boolean isLegalMove;

  @Override
  public void buildResponse() {
  }

  public Square transformSquare() {

  }

  public Piece transformPiece() {
    char identifier = piece.charAt(1);
    Piece targetPiece;

    switch (identifier) {
      case 'P':
        targetPiece = new Pawn();
        break;
      case 'N':
        targetPiece = new Knight();
        break;
      case 'B':
        targetPiece = new Bishop();
        break;
      case 'R':
        targetPiece = new Rook();
        break;
      case 'Q':
        targetPiece = new Queen();
        break;
      case 'K':
        targetPiece = new King();
        break;
      default:
        break;
    }

    return targetPiece;
  }
}
