package com.tco.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import com.tco.model.Inbox;
import com.tco.model.Invitation;
import com.tco.model.Notification;
import com.tco.model.Notification.MessageType;
import com.tco.model.Account;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInbox {
    private Inbox inbox;
    private Notification notif1;
    private Notification notif2;
    private Invitation invite1;
    private Invitation invite2;
    private Account sender;
    private Account receiver;
    private int matchID;

    // private String username = "Rhea9983";
    // private String email = "rhea@gmail.com";
    // private int accountID = 1;

    @BeforeEach
    public void initializeInbox() {
        inbox = new Inbox();
        // sender = new Account();
        matchID = 12;
        notif1 = new Notification(MessageType.INVITE_PENDING, "Rhea Chan");
        notif2 = new Notification(MessageType.INVITE_REJECTED, "Freya LaLuna");
        invite1 = new Invitation(sender, receiver, matchID);
        invite2 = new Invitation(sender, receiver, matchID);
    }
    @Test
    @DisplayName("rheachan9983: Test getNotificationList returns both notifications")
    public void testgetNotificationList(){
        inbox.allNotifs.add(notif1);
        inbox.allNotifs.add(notif2);
        // inbox.getNotificationList();
        assertEquals(inbox.getNotificationList().size(), 2);
    }
    @Test
    @DisplayName("rheachan9983: Test getInviteList returns both invites")
    public void testgetInboxList(){
        inbox.allInvites.add(invite1);
        inbox.allInvites.add(invite2);
        // inbox.getInvitationList();
        assertEquals(inbox.getInvitationList().size(), 2);
    }

    @Test
    @DisplayName("victor45: test addInvite")
    public void testAddInvite() {
        inbox.addInvite(invite1);
        inbox.addInvite(invite2);
        assertEquals(inbox.getInvitationList().size(), 2);
    }

    @Test
    @DisplayName("victor45: test addNotification")
    public void testAddNotification() {
        inbox.addNotification(notif1);
        inbox.addNotification(notif2);
        assertEquals(inbox.getNotificationList().size(), 2);
    }
}
