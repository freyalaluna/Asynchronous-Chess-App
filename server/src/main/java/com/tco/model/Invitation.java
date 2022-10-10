<<<<<<< HEAD:server/src/main/java/com/tco/model/Invitation.java
package com.tco.model;

=======
package com.tco.misc;
>>>>>>> ab2b054 (Changed packages for Notification, Invitation and Inbox to match the rest of the classes):server/src/main/java/com/tco/misc/Invitation.java
import java.time.LocalDateTime;

public final class Invitation {
    private Account inviteCreator;
    private Account inviteRecipient;
    private int matchID;
    private LocalDateTime timestamp;
    private boolean isAccepted;

    public Invitation(Account inviteCreator, Account inviteRecipient, int matchID){
        this.inviteCreator = inviteCreator;
        this.inviteRecipient = inviteRecipient;
        this.matchID = matchID;
        this.timestamp = LocalDateTime.now();

        this.generateNotification(Notification.MessageType.INVITE_PENDING, inviteCreator, inviteRecipient);
    }

    private void acceptInvite(){
        isAccepted = true;
        sendInviteStatus();
    }

    private void rejectInvite(){
        isAccepted = false;
        sendInviteStatus();
    }

    private void sendInviteStatus(){
        if(isAccepted){
            generateNotification(Notification.MessageType.INVITE_ACCEPTED, inviteRecipient, inviteCreator);
        } else {
            generateNotification(Notification.MessageType.INVITE_REJECTED, inviteRecipient, inviteCreator);
        }
    }

    private void generateNotification(Notification.MessageType type, Account toSend, Account toReceive){
        //Notification newNotif = new Notification(type, toSend.username());
        //toReceive.getInbox().addNotification(newNotif);
        return;
    }
}
