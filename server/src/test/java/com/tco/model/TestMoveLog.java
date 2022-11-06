package com.tco.model;

import com.tco.model.Move;
import com.tco.model.MoveLog;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMoveLog {
    private MoveLog moveLog;

    @BeforeEach
    public void beforeEach() {
        this.moveLog = new MoveLog();
    }

    @Test
    @DisplayName("mheavner: initialize with empty array")
    public void testInitializeEmpty() {
        assertEquals(0, moveLog.getMoves().size());
    }

    @Test
    @DisplayName("mheavner: test add move")
    public void testAddMove() {
        Move mockMove = Mockito.mock(Move.class);
        ArrayList mockList = new ArrayList<Move>();
        mockList.add(mockMove);

        moveLog.addMove(mockMove);
        assertEquals(1, moveLog.getMoves().size());
        assertEquals(mockList, moveLog.getMoves());
    }

    @Test
    @DisplayName("mheavner: test constructor with moves")
    public void testConstructorWithMoves() {
        Move mockMove = Mockito.mock(Move.class);
        ArrayList mockList = new ArrayList<Move>();
        mockList.add(mockMove);

        moveLog = new MoveLog(mockList);
        assertEquals(mockList, moveLog.getMoves());
    }

}
