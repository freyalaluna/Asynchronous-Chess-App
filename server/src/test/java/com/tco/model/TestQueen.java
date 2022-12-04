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
        Queen queen = new Queen(false, new Square(3,7));
        ArrayList<Square> result = queen.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("mheavner: test getValidMoves with black queen on starting board")
    public void testGetValidMovesInitialPositionBlack() {
        Queen queen = new Queen(true, new Square(3,0));
        ArrayList<Square> result = queen.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("mheavner: test getValidMoves with white queen on D2")
    public void testGetValidMovesD2() {
        Queen queen = new Queen(false, new Square(3,6));
        ArrayList<Square> result = queen.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        expected.add(new Square(2,5));
        expected.add(new Square(1,4));
        expected.add(new Square(0,3));
        expected.add(new Square(3,5));
        expected.add(new Square(3,4));
        expected.add(new Square(3,3));
        expected.add(new Square(3,2));
        expected.add(new Square(3,1));
        expected.add(new Square(4,5));
        expected.add(new Square(5,4));
        expected.add(new Square(6,3));
        expected.add(new Square(7,2));
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("mheavner: test getValidMoves with black queen on C6")
    public void testGetValidMovesC6() {
        Queen queen = new Queen(true, new Square(2,2));
        ArrayList<Square> result = queen.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        expected.add(new Square(1,2));
        expected.add(new Square(0,2));
        expected.add(new Square(1,3));
        expected.add(new Square(0,4));
        expected.add(new Square(2,3));
        expected.add(new Square(2,4));
        expected.add(new Square(2,5));
        expected.add(new Square(2,6));
        expected.add(new Square(3,2));
        expected.add(new Square(4,2));
        expected.add(new Square(5,2));
        expected.add(new Square(6,2));
        expected.add(new Square(7,2));
        expected.add(new Square(3,3));
        expected.add(new Square(4,4));
        expected.add(new Square(5,5));
        expected.add(new Square(6,6));
        assertEquals(expected, result);
    }
}
