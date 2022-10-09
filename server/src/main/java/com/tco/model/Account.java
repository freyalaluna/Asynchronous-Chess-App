package com.tco.model;

import java.util.ArrayList;
import com.tco.model.MatchHistory;
// import com.tco.model.Inbox;
import com.tco.model.MatchRecord;
import com.tco.model.Notification;

public class Account {
    private String username;
    private String email;
    private int accountID;

    public Account(String username, String email, int accountID){
        this.username = username;
        this.email = email;
        this.accountID= accountID;
    }

    public ArrayList<MatchRecord> getMatchHistory() {
        ArrayList<MatchRecord> matchHistory = new ArrayList<MatchRecord>();
        return matchHistory;
    }

    //needs to return type Inbox
    public ArrayList<Notification> getInbox(){
        ArrayList<Notification> inbox = new ArrayList<Notification>();
        return inbox;
    }
}
