package com.tco.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TestKnight {
  private static Board board = new Board();
  private static char[][] boardState;

  @BeforeEach
  public void resetBoardState() {
    boardState = board.getBoardState();
  }

  @Test
  @DisplayName("victor45: test getPossibleMoves() for left white knight in initial position")
  public void testValidMovesWhiteKnightB1() {
    Knight knight = new Knight(false, new Square(1, 7));
    ArrayList<Square> result = knight.getPossibleMoves(boardState);
    ArrayList<Square> expected = new ArrayList<>();
    expected.add(new Square(0, 5));
    expected.add(new Square(2, 5));
    assertTrue(expected.size() == result.size() && expected.containsAll(result) && result.containsAll(expected));
  }

  @Test
  @DisplayName("victor45: test getPossibleMoves() for right black knight in initial position")
  public void testValidMovesBlackKnightG8() {
    Knight knight = new Knight(true, new Square(6, 0));
    ArrayList<Square> result = knight.getPossibleMoves(boardState);
    ArrayList<Square> expected = new ArrayList<>();
    expected.add(new Square(5, 2));
    expected.add(new Square(7, 2));
    assertTrue(expected.size() == result.size() && expected.containsAll(result) && result.containsAll(expected));
  }

  @Test
  @DisplayName("victor45: test getPossibleMoves() for right black knight in middle of board")
  public void testValidMovesBlackKnightE3() {
    Knight knight = new Knight(true, new Square(4, 5));
    ArrayList<Square> result = knight.getPossibleMoves(boardState);
    ArrayList<Square> expected = new ArrayList<>();
    expected.add(new Square(3, 3));
    expected.add(new Square(5, 3));
    expected.add(new Square(6, 4));
    expected.add(new Square(6, 6));
    expected.add(new Square(5, 7));
    expected.add(new Square(3, 7));
    expected.add(new Square(2, 6));
    expected.add(new Square(2, 4));
    assertTrue(expected.size() == result.size() && expected.containsAll(result) && result.containsAll(expected));
  }

}
