package com.tco.model;

import com.tco.model.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {
    private Player player;

    @BeforeEach
    public void beforeEach() {
        player = new Player();
    }

    @Test
    @DisplayName("mheavner: Test initial values")
    public void testInitialValues() {
        assertEquals(1, player.getAccountID());
        assertEquals("User", player.getUsername());
        assertEquals(true, player.getColor());
    }

    @Test
    @DisplayName("mheavner: Test constructor w/ args")
    public void testConstructorWithArgs() {
        player = new Player(10, "User10", false);
        assertEquals(10, player.getAccountID());
        assertEquals("User10", player.getUsername());
        assertEquals(false, player.getColor());
    }
}
