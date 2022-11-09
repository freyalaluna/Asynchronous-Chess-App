package com.tco.model;

import com.tco.model.MatchHistory;
import com.tco.model.MatchRecord;
import com.tco.model.MoveLog;
import com.tco.model.Player;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestMatchHistory {
    private MatchHistory matchHistory;
    private MatchRecord matchRecord;
    private MoveLog moveLog;
    private Player player1;
    private Player player2;


    @BeforeEach
    public void beforeEach() {
        matchHistory = new MatchHistory();
        moveLog = new MoveLog();
        player1 = new Player(1, "player1", true);
        player2 = new Player(2, "player2", false);
        matchRecord = new MatchRecord(moveLog,player1,player2);
    }

    @Test
    @DisplayName("rchan: test getWins()")
    public void testGetWins() {
        matchHistory.history.add(matchRecord);
        assertEquals(matchHistory.getWins(),0);
    }

    @Test
    @DisplayName("rchan: test getLosses()")
    public void testGetLosses() {
        matchHistory.history.add(matchRecord);
        assertEquals(matchHistory.getLosses(),0);
    }

    @Test
    @DisplayName("rchan: test createTimeline()")
    public void testCreateTimeline() {
        assertEquals(matchHistory.createTimeline(),"");
    }

}
