package com.tco.model;

import java.util.ArrayList;

public class Inbox {
    ArrayList<Notification> allNotifs;
    ArrayList<Invitation> allInvites;

    public Inbox(){
        allNotifs = new ArrayList<Notification>();
        allInvites = new ArrayList<Invitation>();
    }

    public void addInvite(Invitation newInvite){
        //TODO: implement this
    }

    public void addNotification(Notification newNotif){
        //TODO: implement this
    }

    public ArrayList<Notification> getNotificationList(){
        return allNotifs;
    }

    public ArrayList<Invitation> getInvitationList(){
        return allInvites;
    }
}
