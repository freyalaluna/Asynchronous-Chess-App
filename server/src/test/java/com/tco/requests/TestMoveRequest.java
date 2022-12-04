package com.tco.requests;

import java.util.Arrays;
import com.tco.model.Board;
import com.tco.model.Square;
import com.tco.model.Piece;
import com.tco.model.Pawn;
import com.tco.model.Knight;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMoveRequest {
  private MoveRequest req;

  @BeforeEach
  public void createRequestForTestCases() {
    req = new MoveRequest();
    req.setTargetSquare("e4");
  }

  @Test
  @DisplayName("victor45: test square transform")
  public void testSquareTransform() {
    String squareString = "d4";
    Square squareObj = req.transformSquare(squareString);
    assertEquals(squareObj.getX(), 3);
    assertEquals(squareObj.getY(), 4);
  }

  @Test
  @DisplayName("victor45: test piece transform black knight")
  public void testPieceTransformBlackKnight() {
    String pieceString = "bN";
    req.setPiece(pieceString);
    Piece pieceObj = req.transformPiece(new Square(0, 0));
    assertTrue(pieceObj instanceof Knight);
    assertEquals(pieceObj.getColor(), true);
  }

  @Test
  @DisplayName("victor45: test piece transform white pawn")
  public void testPieceTransformWhitePawn() {
    String pieceString = "wP";
    req.setPiece(pieceString);
    Piece pieceObj = req.transformPiece(new Square(0, 0));
    assertTrue(pieceObj instanceof Pawn);
    assertEquals(pieceObj.getColor(), false);
  }

  @Test
  @DisplayName("victor45: test transform game state initial state")
  public void testGameTransformStartingState() {
    char[][] expectedState = {
      {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
      {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
      {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
      {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
      {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
      {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
      {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
      {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
    };

    String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    char[][] actualState = req.transformGameState(fenString);
    assertTrue(Arrays.deepEquals(expectedState, actualState));
  }

  @Test
  @DisplayName("victor45: test transform game state middle of game w KQkq - 0 1")
  public void testGameTransformMidGameState() {
    char[][] expectedState = {
      {'r', 'n', 'b', 'o', 'k', 'b', 'n', 'r'},
      {'p', 'p', 'p', 'o', 'o', 'p', 'p', 'p'},
      {'o', 'o', 'o', 'p', 'o', 'o', 'o', 'o'},
      {'o', 'o', 'o', 'o', 'p', 'o', 'q', 'o'},
      {'o', 'o', 'B', 'o', 'P', 'o', 'o', 'o'},
      {'o', 'o', 'N', 'o', 'o', 'N', 'o', 'o'},
      {'P', 'P', 'P', 'P', 'o', 'P', 'P', 'P'},
      {'R', 'o', 'B', 'Q', 'K', 'o', 'o', 'R'}
    };

    String fenString = "rnb1kbnr/ppp2ppp/3p4/4p1q1/2B1P3/2N2N2/PPPP1PPP/R1BQK2R w KQkq - 0 1";
    char[][] actualState = req.transformGameState(fenString);
    assertTrue(Arrays.deepEquals(expectedState, actualState));
  }

  @Test
  @DisplayName("victor45: test transform game state only kings left")
  public void testGameTransformOnlyKings() {
    char[][] expectedState = {
      {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
      {'o', 'k', 'o', 'o', 'o', 'o', 'o', 'o'},
      {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
      {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
      {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
      {'o', 'o', 'o', 'K', 'o', 'o', 'o', 'o'},
      {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
      {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'}
    };

    String fenString = "8/1k6/8/8/8/3K4/8/8 w KQkq - 0 1";
    char[][] actualState = req.transformGameState(fenString);
    assertTrue(Arrays.deepEquals(expectedState, actualState));
  }

  @Test
  @DisplayName("victor45: test validate move white pawn to e4 - valid move")
  public void testValidateMoveWhitePawnToE4() {
    Piece whitePawnE2 = new Pawn(false, new Square(4, 6));
    Square e4Square = new Square(4, 4);
    Board board = new Board();
    char[][] initialBoardState = board.getBoardState();
    req.validateMove(whitePawnE2, e4Square, initialBoardState);
    assertEquals(req.getIsLegalMove(), true);
  }

  @Test
  @DisplayName("victor45: test validate move black knight to g6 - invalid move")
  public void testValidateMoveBlackKnightToG6() {
    Piece blackKnightG8 = new Knight(true, new Square(6, 0));
    Square g6Square = new Square(6, 2);
    Board board = new Board();
    char[][] initialBoardState = board.getBoardState();
    req.validateMove(blackKnightG8, g6Square, initialBoardState);
    assertEquals(req.getIsLegalMove(), false);
  }

  @Test
  @DisplayName("victor45: test build resposne for white pawn to d4 - valid move")
  public void testBuildResponseForWhitePawnD4Move() {
    req.setSourceSquare("d2");
    req.setTargetSquare("d4");
    req.setPiece("wP");
    req.setGameState("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    req.buildResponse();
    assertEquals(true, req.getIsLegalMove());
  }
}
