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
    private Invitation invite1;
    private Account sender;
    private Account receiver;
    private int matchID;
    private String username = "Rhea9983";
    private String email = "rhea@gmail.com";
    private int accountID = 1;

    @BeforeEach
    public void initializeInbox() {
        inbox = new Inbox();
        // sender = new Account();
        matchID = 12;
        notif1 = new Notification(MessageType.INVITE_PENDING, "Rhea Chan");
        invite1 = new Invitation(sender, receiver, matchID);
    }
}
