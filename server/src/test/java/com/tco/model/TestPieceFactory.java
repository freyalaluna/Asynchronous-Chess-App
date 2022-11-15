package com.tco.model;

import com.tco.model.Square;
import com.tco.model.Piece;
import com.tco.model.Pawn;
import com.tco.model.Knight;
import com.tco.model.Bishop;
import com.tco.model.Rook;
import com.tco.model.Queen;
import com.tco.model.King;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TestPieceFactory {
  private static PieceFactory factory = new PieceFactory();
  private static Square piecePosition = new Square(0, 0);

  @Test
  @DisplayName("victor45: test factory creation of white pawn")
  public void testCreateWhitePawn() {
    Piece testPiece = factory.createPieceFENType('P', false, piecePosition);

    assertTrue(testPiece instanceof Pawn);
    assertTrue(!testPiece.getColor());
  }

  @Test
  @DisplayName("victor45: test factory creation of black knight")
  public void testCreateBlackKnight() {
    Piece testPiece = factory.createPieceFENType('N', true, piecePosition);

    assertTrue(testPiece instanceof Knight);
    assertTrue(testPiece.getColor());
  }

  @Test
  @DisplayName("victor45: test factory creation of white bishop")
  public void testCreateWhiteBishop() {
    Piece testPiece = factory.createPieceFENType('B', false, piecePosition);

    assertTrue(testPiece instanceof Bishop);
    assertTrue(!testPiece.getColor());
  }

  @Test
  @DisplayName("victor45: test factory creation of black rook")
  public void testCreateBlackRook() {
    Piece testPiece = factory.createPieceFENType('R', true, piecePosition);

    assertTrue(testPiece instanceof Rook);
    assertTrue(testPiece.getColor());
  }

  @Test
  @DisplayName("victor45: test factory creation of white queen")
  public void testCreateWhiteQueen() {
    Piece testPiece = factory.createPieceFENType('Q', false, piecePosition);

    assertTrue(testPiece instanceof Queen);
    assertTrue(!testPiece.getColor());
  }

  @Test
  @DisplayName("victor45: test factory creation of black king")
  public void testCreateBlackKing() {
    Piece testPiece = factory.createPieceFENType('K', true, piecePosition);

    assertTrue(testPiece instanceof King);
    assertTrue(testPiece.getColor());
  }

  @Test
  @DisplayName("victor45: test factory creation of invalid piece")
  public void testCreateInvalidPiece() {
    Piece testPiece = factory.createPieceFENType('Z', true, piecePosition);

    assertTrue(testPiece == null);
  }

}
