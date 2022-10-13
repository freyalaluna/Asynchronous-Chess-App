package com.tco.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TestPawn {
    private static Board board = new Board();
    private static char[][] boardState;

    @BeforeEach
    public void resetBoardState() {
        boardState = board.getBoardState();
    }

    @Test
    @DisplayName("fturner: test white pawn at starting location d2")
    public void testGetValidMovesWhiteD2(){
        char[][] newBoard = board.getBoardState();
        Pawn pawn = new Pawn(false, new Square(3,6));
        ArrayList<Square> result = pawn.getPossibleMoves(newBoard);
        ArrayList<Square> expected = new ArrayList<Square>();
        expected.add(new Square(3, 5));
        expected.add(new Square(3, 4));

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("fturner: test black pawn at starting location d7")
    public void testGetValidMovesBlackD7(){
        char[][] newBoard = board.getBoardState();
        Pawn pawn = new Pawn(true, new Square(3,1));
        ArrayList<Square> result = pawn.getPossibleMoves(newBoard);
        ArrayList<Square> expected = new ArrayList<Square>();
        expected.add(new Square(3, 2));
        expected.add(new Square(3, 3));

        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <=7; j++){
                System.out.print(newBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("fturner: test captures as valid moves in the middle of the board for white")
    public void testGetValidCapturesWhite(){
        char[][] newBoard = board.getBoardState();
        Pawn pawn = new Pawn(false, new Square(3,4));
        pawn.setCanDoubleMove(false);

        newBoard[3][2] = 'p';
        newBoard[3][4] = 'p';
        ArrayList<Square> result = pawn.getPossibleMoves(newBoard);
        ArrayList<Square> expected = new ArrayList<Square>();

        expected.add(new Square(3, 3));
        expected.add(new Square(2, 3));
        expected.add(new Square(4, 3));

        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <=7; j++){
                System.out.print(newBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("fturner: test captures as valid moves in the middle of the board for black")
    public void testGetValidCapturesBlack(){
        char[][] newBoard = board.getBoardState();
        Pawn pawn = new Pawn(true, new Square(3,3));
        pawn.setCanDoubleMove(false);

        newBoard[4][2] = 'P';
        newBoard[4][4] = 'P';
        ArrayList<Square> result = pawn.getPossibleMoves(newBoard);
        ArrayList<Square> expected = new ArrayList<Square>();

        expected.add(new Square(3, 4));
        expected.add(new Square(2, 4));
        expected.add(new Square(4, 4));

        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <=7; j++){
                System.out.print(newBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("fturner: test en passant as a valid move for white")
    public void testEnPassantsWhite(){
        char[][] newBoard = board.getBoardState();
        Pawn pawn = new Pawn(false, new Square(3, 3));
        pawn.setCanDoubleMove(false);

        newBoard[3][2] = 'p';
        newBoard[1][2] = 'd';
        newBoard[3][4] = 'p';
        newBoard[1][4] = 'd';

        ArrayList<Square> result = pawn.getPossibleMoves(newBoard);
        ArrayList<Square> expected = new ArrayList<Square>();

        expected.add(new Square(3, 2));
        expected.add(new Square(2, 2));
        expected.add(new Square(4, 2));

        resetBoardState();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("fturner: test en passant as a valid move for black")
    public void testEnPassantBlack(){
        char[][] newBoard = board.getBoardState();
        Pawn pawn = new Pawn(true, new Square(3, 4));
        pawn.setCanDoubleMove(false);

        newBoard[4][2] = 'P';
        newBoard[6][2] = 'D';
        newBoard[4][4] = 'P';
        newBoard[6][4] = 'D';

        ArrayList<Square> result = pawn.getPossibleMoves(newBoard);
        ArrayList<Square> expected = new ArrayList<Square>();

        expected.add(new Square(3, 5));
        expected.add(new Square(2, 5));
        expected.add(new Square(4, 5));

        resetBoardState();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("fturner: test that two pawns facing each other cannot move forward")
    public void testBlocked(){
        char[][] newBoard = board.getBoardState();
        Pawn whitePawn = new Pawn(false, new Square(3, 4));
        Pawn blackPawn = new Pawn(true, new Square(3, 3));

        newBoard[3][3] = 'p';
        newBoard[3][4] = 'P';

        ArrayList<Square> whiteExpected = new ArrayList<Square>();
        ArrayList<Square> whiteResult = whitePawn.getPossibleMoves(newBoard);
        ArrayList<Square> blackExpected = new ArrayList<Square>();
        ArrayList<Square> blackResult = blackPawn.getPossibleMoves(newBoard);

        resetBoardState();
        assertEquals(whiteExpected, whiteResult);
        assertEquals(blackExpected, blackResult);
    }
}
