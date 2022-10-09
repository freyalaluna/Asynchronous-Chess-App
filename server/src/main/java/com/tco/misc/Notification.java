package com.tco.misc;
import java.time.LocalDateTime;

public final class Notification {
    private String messageBody;
    private MessageType type;
    private LocalDateTime timestamp;

    public enum MessageType{
        INVITE_PENDING,
        INVITE_REJECTED,
        INVITE_ACCEPTED,
        TURN_NOTIFICATION;
    }

    public Notification(MessageType type, String senderName){
        this.type = type;
        this.messageBody = this.generateMessageTemplate(senderName);
        this.timestamp = LocalDateTime.now();
    }

    private String generateMessageTemplate(String senderName){
        switch(type){
            case INVITE_PENDING: return "Invite from " + senderName;
            case INVITE_REJECTED: return "Invite to " + senderName + " rejected";
            case INVITE_ACCEPTED: return "Invite to " + senderName + " accepted";
            case TURN_NOTIFICATION: return "It is your turn in game with " + senderName;
        }
    }

    public String getMessageBody(){
        return messageBody;
    }

    public void setMessageBody(String toSet){
        messageBody = toSet;
    }

}
