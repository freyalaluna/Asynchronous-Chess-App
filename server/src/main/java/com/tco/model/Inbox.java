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
        allInvites.add(newInvite);
    }

    public void addNotification(Notification newNotif){
        allNotifs.add(newNotif);
    }

    public ArrayList<Notification> getNotificationList(){
        return allNotifs;
    }

    public ArrayList<Invitation> getInvitationList(){
        return allInvites;
    }
}
