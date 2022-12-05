package com.tco.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TestBishop {
    
    private static Board board = new Board();
    private static char[][] boardState;

    @BeforeEach
    public void resetBoardState() {
        boardState = board.getBoardState();
    }

    @Test
    @DisplayName("craetz: test getPossibleMoves for left white bishop in starting position")
    public void testValidMovesWhiteBishopC1(){
        Bishop bishop = new Bishop(false, new Square(2,7));
        ArrayList<Square> result = bishop.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("craetz: test getPossibleMoves for left black bishop in starting position")
    public void testValidMovesBlackBishopC8(){
        Bishop bishop = new Bishop(true,new Square(2,0));
        ArrayList<Square> result = bishop.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("craetz: test getPossibleMoves for white bishop mid-board")
    public void testValidMovesWhiteBishopF4()
    {
        Bishop bishop = new Bishop(true, new Square(4,5));
        ArrayList<Square> result = bishop.getPossibleMoves(boardState);
        ArrayList<Square> expected = new ArrayList<Square>();
        expected.add(new Square(2,3));
        expected.add(new Square(3,4));
        expected.add(new Square(2,7));
        expected.add(new Square(3,6));
        expected.add(new Square(5,4));
        expected.add(new Square(5,6));
    } 
}
