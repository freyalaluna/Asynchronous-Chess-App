package com.tco.model;

import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestBoard {
  private static Board board;

  @BeforeEach
  public void resetBoardState() {
    board = new Board();
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
    board.validateIncomingMove("e2", "e4", "wP", fenString);
    assertTrue(Arrays.deepEquals(expectedState, board.getBoardState()));
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
    board.validateIncomingMove("a2", "a4", "wP", fenString);
    assertTrue(Arrays.deepEquals(expectedState, board.getBoardState()));
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
    board.validateIncomingMove("b7", "b6", "bK", fenString);
    assertTrue(Arrays.deepEquals(expectedState, board.getBoardState()));
  }

  @Test
  @DisplayName("victor45: test validate move white pawn to e4 - valid move")
  public void testValidateMoveWhitePawnToE4() {
    String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    boolean[] result = board.validateIncomingMove("e2", "e4", "wP", fenString);
    assertEquals(true, result[0]);  //invalid move
    assertEquals(false, result[1]); //game is not over
  }

  @Test
  @DisplayName("victor45: test validate move black knight to g6 - invalid move")
  public void testValidateMoveBlackKnightToG6() {
    String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    boolean[] result = board.validateIncomingMove("g8", "g6", "bK", fenString);
    assertEquals(false, result[0]);  //invalid move
    assertEquals(false, result[1]); //game is not over
  }

  @Test
  @DisplayName("victor45: test validate move white queen to g6 - valid game-winning move")
  public void testValidateMoveWinGameWithWhiteQueenToG4() {
    String fenString = "rnb1kbnr/pppp1ppp/8/4p3/4PQq1/8/PPPP1PPP/RNB1KBNR w KQkq - 4 4";
    boolean[] result = board.validateIncomingMove("f4", "g4", "wQ", fenString);
    assertEquals(true, result[0]);  //invalid move
    assertEquals(true, result[1]); //game is over
  }

}
