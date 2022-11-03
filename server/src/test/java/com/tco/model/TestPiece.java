package com.tco.model;

import com.tco.model.Queen;
import com.tco.model.Square;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPiece {
    private Piece piece;

    @BeforeEach
    public void beforeEach() {
        this.piece = new Queen(true, new Square(1,1));
    }

    @Test
    @DisplayName("mheavner: test getColor")
    public void testGetColor() {
        assertEquals(true, piece.getColor());
    }
    
    @Test
    @DisplayName("mheavner: test position setter and getter")
    public void testPosition() {
        Square beforeSquare = new Square(1,1);
        assertEquals(beforeSquare, piece.getPosition());

        Square afterSquare = new Square(2,2);
        piece.setPosition(new Square(2,2));
        assertEquals(afterSquare, piece.getPosition());
    }
}
