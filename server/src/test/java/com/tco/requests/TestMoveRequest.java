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
  }

  @Test
  @DisplayName("victor45: test validate move black knight to g6 - invalid move")
  public void testBuildResponseForBlackKnightG6Move() {
    req.setSourceSquare("g8");
    req.setTargetSquare("g6");
    req.setPiece("bK");
    req.setGameState("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    req.buildResponse();
    assertEquals(false, req.getIsLegalMove());
    assertEquals(false, req.getIsGameOver());
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
    assertEquals(false, req.getIsGameOver());
  }

  @Test
  @DisplayName("victor45: test build response for white queen to g4 - valid game-winning move")
  public void testBuildResponseForWhiteQueenG4WinningMove() {
    req.setSourceSquare("f4");
    req.setTargetSquare("g4");
    req.setPiece("wQ");
    req.setGameState("rnb1kbnr/pppp1ppp/8/4p3/4PQq1/8/PPPP1PPP/RNB1KBNR w KQkq - 4 4");
    req.buildResponse();
    assertEquals(true, req.getIsLegalMove());
    assertEquals(true, req.getIsGameOver());
  }
}
