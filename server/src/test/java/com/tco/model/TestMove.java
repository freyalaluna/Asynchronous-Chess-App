package com.tco.model;

import com.tco.model.Move;
import com.tco.model.Piece;
import com.tco.model.Square;


import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestMove {

    private Move move;

    @BeforeEach
    public void beforeEach() {
        Pawn pawn = new Pawn(true,new Square(2,1));
        Square start = new Square(2,1);
        Square end = new Square(3,1);
        boolean didCapture = false;
        LocalDateTime date = LocalDateTime.of(2022,1,1,1,1);
        this.move = new Move(pawn,start,end,didCapture,date);
    }

    @Test
    @DisplayName("craetz: Test getPiece()")
    public void testGetPiece(){
        Pawn piece = new Pawn(true,new Square(2,1));
        assertEquals(move.getPiece().getColor(),piece.getColor());
    }

    @Test 
    @DisplayName("craetz: test getStartCoord()")
    public void testGetStartCoord() {
        assertEquals(move.getStartCoord(), new Square(2,1));
    }

    @Test 
    @DisplayName("craetz: test getEndCoord()")
    public void testGetEndCoord() {
        assertEquals(move.getEndCoord(), new Square(3,1));
    }

    @Test 
    @DisplayName("craetz: test captured()")
    public void testCaptured() {
        assertEquals(move.captured(), false);
    }

    @Test 
    @DisplayName("craetz: test getTimestamp()")
    public void testGetTimestamp() {
        LocalDateTime date = LocalDateTime.of(2022,1,1,1,1);
        assertEquals(move.getTimestamp().getMonth(), date.getMonth());
    }
    
}
