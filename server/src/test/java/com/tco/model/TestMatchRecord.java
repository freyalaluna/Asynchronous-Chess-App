package com.tco.model;

import com.tco.model.MatchRecord;
import com.tco.model.MoveLog;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestMatchRecord {
    private MatchRecord matchRecord;


    @BeforeEach
    public void beforeEach() {
        MoveLog moveLog = Mockito.mock(MoveLog.class);
        Player winner = new Player(1,"user1",false);
        Player loser = new Player(2,"user2",true);
        this.matchRecord = new MatchRecord(moveLog,winner,loser);
    }

    @Test
    @DisplayName("craetz: test getWinner()")
    public void testGetWinner() {
        assertEquals(matchRecord.getWinner().getAccountID(),1);
    }

    @Test
    @DisplayName("craetz: test getLoser()")
    public void testGetLoser() {
        assertEquals(matchRecord.getLoser().getAccountID(),2);
    }


}
