package com.tco.model;

import com.tco.model.Notification;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNotification {
    private Notification notif;
    
    @BeforeEach
    public void beforeEach(){
        
    }

    @Test
    @DisplayName("fturner: Test initialization with INVITE_PENDING")
    public void testInvitePending(){
        this.notif = new Notification(Notification.MessageType.INVITE_PENDING, "Freya");
        assertEquals(this.notif.getMessageBody(), "Invite from Freya");
    }

    @Test
    @DisplayName("fturner: Test initialization with INVITE_REJECTED")
    public void testInviteRejected(){
        this.notif = new Notification(Notification.MessageType.INVITE_REJECTED, "Freya");
        assertEquals(this.notif.getMessageBody(), "Invite to Freya rejected");
    }    

    @Test
    @DisplayName("fturner: Test initialization with INVITE_ACCEPTED")
    public void testInviteAccpeted(){
        this.notif = new Notification(Notification.MessageType.INVITE_ACCEPTED, "Freya");
        assertEquals(this.notif.getMessageBody(), "Invite to Freya accepted");
    }

    @Test
    @DisplayName("fturner: Test initialization with TURN_NOTIFICATION")
    public void testTurnNotification(){
        this.notif = new Notification(Notification.MessageType.TURN_NOTIFICATION, "Freya");
        assertEquals(this.notif.getMessageBody(), "It is your turn in game with Freya");
    }

    @Test
    @DisplayName("fturner: Test that setMessageBody overwrites the message string stored in Notification")
    public void testGetterAndSetter(){
        this.notif = new Notification(Notification.MessageType.TURN_NOTIFICATION, "Freya");
        String toSet = "This is a test";
        this.notif.setMessageBody(toSet);
        assertEquals(notif.getMessageBody(), toSet);
    }
}
