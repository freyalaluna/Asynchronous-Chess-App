package com.tco.model;

import com.tco.model.Square;
import com.tco.model.Piece;
import com.tco.model.Pawn;
import com.tco.model.Knight;
import com.tco.model.Bishop;
import com.tco.model.Rook;
import com.tco.model.Queen;
import com.tco.model.King;

public class PieceFactory {
  public Piece createPieceFENType(char type, boolean color, Square position) {
    Piece piece = null;

    if (type == 'P') {
      piece = new Pawn(color, position);
    } else if (type == 'N') {
      piece = new Knight(color, position);
    } else if (type == 'B') {
      piece = new Bishop(color, position);
    } else if (type == 'R') {
      piece = new Rook(color, position);
    } else if (type == 'Q') {
      piece = new Queen(color, position);
    } else if (type == 'K') {
      piece = new King(color, position);
    }

    return piece;
  }
}
