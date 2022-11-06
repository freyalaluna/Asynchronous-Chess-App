package com.tco.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import com.tco.model.Player;
import com.tco.model.Match;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestMatch {
    private Player player1;
    private Player player2;
    private Match match;
    int matchID;

  
    @BeforeEach
    public void initializePlayer() {
        player1 = new Player();
        player2 = new Player();
        matchID = 10;
    }
    @Test
    @DisplayName("rheachan9983: Test getCurrentPlayer")
    public void testGetCurrentPlayer() {
        match = new Match(player1, player2, matchID);
        assertEquals(0, match.getCurrentPlayer());
    }
}
