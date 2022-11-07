package com.tco.model;

import org.junit.jupiter.api.Test;

import com.tco.model.Account;
import com.tco.model.MatchHistory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TestAccount {
    private static Account account;
    
    @BeforeEach
    public void resetAccount(){
        this.account = new Account("freyalaluna", "fturner@colostate.edu", 1);
    }

    @Test
    @DisplayName("fturner: Test that constructor sets values properly")
    public void testConstructorValues(){
        assertEquals(account.getUsername(), "freyalaluna");
        assertEquals(account.getEmail(), "fturner@colostate.edu");
        assertEquals(account.getAccountID(), 1);
    }

    @Test
    @DisplayName("fturner: Test that getMatchHistory returns an empty match history")
    public void testGetMatchHistory(){
        ArrayList<MatchRecord> thisMatch = account.getMatchHistory();
        assertEquals(thisMatch.size(), 0);
    }

    @Test
    @DisplayName("fturner: Test that getInbox returns an empty arraylist of Notifications")
    public void testGetInbox(){
        ArrayList<Notification> thisInbox = account.getInbox();
        assertEquals(thisInbox.size(), 0);
    }
}
