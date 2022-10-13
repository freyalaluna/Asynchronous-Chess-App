package com.tco.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TestKing {
    
    private static Board board;
    private static char[][] boardState;

    @BeforeEach
    public void resetBoardState() {
        board = new Board();
    }

    @Test
    @DisplayName("fturner: test king's moves in the center of the board")
    public void testValidCenterMoves(){
        char[][] newBoard = board.getBoardState();
        King king = new King(false, new Square(4, 3));

        ArrayList<Square> result = king.getPossibleMoves(newBoard);
        ArrayList<Square> expected = new ArrayList<Square>();
        expected.add(new Square(5, 4));
        expected.add(new Square(4, 4));
        expected.add(new Square(3, 4));
        expected.add(new Square(5, 3));
        expected.add(new Square(3, 3));
        expected.add(new Square(5, 2));
        expected.add(new Square(4, 2));
        expected.add(new Square(3, 2));

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("fturner: tests that king cannot move off the edge of the board")
    public void testValidEdgeMoves(){
        char[][] newBoard = board.getBoardState();
        King king = new King(false, new Square(0, 0));

        newBoard[0][1] = 'o';
        newBoard[1][1] = 'o';
        newBoard[1][0] = 'o';

        ArrayList<Square> result = king.getPossibleMoves(newBoard);
        ArrayList<Square> expected = new ArrayList<Square>();

        expected.add(new Square(1, 1));
        expected.add(new Square(0, 1));
        expected.add(new Square(1, 0));

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("fturner: tests that king cannot move if surrounded by allied pieces")
    public void testWhenBlocked(){
        char[][] newBoard = board.getBoardState();
        King king = new King(false, new Square(0, 4));

        ArrayList<Square> result = king.getPossibleMoves(newBoard);
        ArrayList<Square> expected = new ArrayList<Square>();

        assertEquals(result, expected);
    }
}
