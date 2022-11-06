package com.tco.model;

import com.tco.model.Invitation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInvitation {
    private static Account sender;
    private static Account receiver;
    private static Invitation invite;

    @BeforeEach
    public void setInviteVars(){
        this.sender = new Account("Sender", "sender@colostate.edu", 1);
        this.receiver = new Account("Receiver", "receiver@colostate.edu", 2);
        this.invite = new Invitation(this.sender, this.receiver, 1);
    }

    @Test
    @DisplayName("fturner: Test that invite.isAccepted is changed to true on acceptInvite()")
    public void testAcceptInvite(){
        this.invite.acceptInvite();
        boolean acceptStatus = this.invite.getAcceptedStatus();
        assertEquals(acceptStatus, true);
    }

    @Test
    @DisplayName("fturner: Test that invite.isAccepted is changed to false on rejectInvite()")
    public void testRejectInvite(){
        this.invite.rejectInvite();
        boolean rejectStatus = this.invite.getAcceptedStatus();
        assertEquals(rejectStatus, false);
    }

    //TODO: Write tests for generateNotification once it is implemented
}
