package com.tco.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
public class TestRook {
    private static Board board = new Board();
    private static char[][] boardState;
  
    @BeforeEach
    public void resetBoardState() {
      boardState = board.getBoardState();
    }
    @Test
    @DisplayName("rheachan9983: test getValidMoves with black rook in starting position A8")
    public void testGetPossibleMovesBlackRookStarting() {
        Rook rook = new Rook(true, new Square(7,7));
        ArrayList<Square> result = rook.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        assertTrue(expected.size() == result.size() && expected.containsAll(result) && result.containsAll(expected));
    }

    @Test
    @DisplayName("rheachan9983: test getValidMoves with white rook in starting position H1")
    public void testGetPossibleMovesWhiteRookStarting() {
        Rook rook = new Rook(false, new Square(0,0));
        ArrayList<Square> result = rook.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        assertTrue(expected.size() == result.size() && expected.containsAll(result) && result.containsAll(expected));
    }

    // @Test
    // @DisplayName("rheachan9983: test getPossibleMoves() for black rook on E5")
    // public void testValidMovesRookToRight() {
    //   Rook rook = new Rook(false, new Square(4,4));
    //   ArrayList<Square> result = rook.getPossibleMoves(boardState);
    //   ArrayList<Square> expected = new ArrayList<>();
    //   expected.add(new Square(4, 5));
    //   assertTrue(expected.size() == result.size() && expected.containsAll(result) && result.containsAll(expected));
    // }

}

