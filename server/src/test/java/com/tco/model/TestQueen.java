package com.tco.model;

import com.tco.model.Board;
import com.tco.model.Queen;
import com.tco.model.Square;

import java.lang.reflect.Type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestQueen {
    private static Board board = new Board();
    private static char[][] boardState;

    @BeforeEach
    public void resetBoardState() {
        boardState = board.getBoardState();
    }

    @Test
    @DisplayName("mheavner: test getValidMoves with white queen on starting board")
    public void testGetValidMovesInitialPositionWhite() {
        Queen queen = new Queen(false, new Square(7,3));
        ArrayList<Square> result = queen.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("mheavner: test getValidMoves with black queen on starting board")
    public void testGetValidMovesInitialPositionBlack() {
        Queen queen = new Queen(true, new Square(0,3));
        ArrayList<Square> result = queen.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("mheavner: test getValidMoves with white queen on D2")
    public void testGetValidMovesD2() {
        Queen queen = new Queen(false, new Square(6,3));
        ArrayList<Square> result = queen.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        expected.add(new Square(5,2));
        expected.add(new Square(4,1));
        expected.add(new Square(3,0));
        expected.add(new Square(5,3));
        expected.add(new Square(4,3));
        expected.add(new Square(3,3));
        expected.add(new Square(2,3));
        expected.add(new Square(1,3));
        expected.add(new Square(5,4));
        expected.add(new Square(4,5));
        expected.add(new Square(3,6));
        expected.add(new Square(2,7));
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("mheavner: test getValidMoves with black queen on C6")
    public void testGetValidMovesC6() {
        Queen queen = new Queen(true, new Square(2,2));
        ArrayList<Square> result = queen.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        expected.add(new Square(2,1));
        expected.add(new Square(2,0));
        expected.add(new Square(2,3));
        expected.add(new Square(2,4));
        expected.add(new Square(2,5));
        expected.add(new Square(2,6));
        expected.add(new Square(2,7));
        expected.add(new Square(3,1));
        expected.add(new Square(4,0));
        expected.add(new Square(3,2));
        expected.add(new Square(4,2));
        expected.add(new Square(5,2));
        expected.add(new Square(6,2));
        expected.add(new Square(3,3));
        expected.add(new Square(4,4));
        expected.add(new Square(5,5));
        expected.add(new Square(6,6));
        assertEquals(expected, result);
    }
}
