public final class Notification {
    private String messageBody;
    private messageType type;

    public enum messageType{
        INVITE_PENDING,
        INVITE_REJECTED,
        INVITE_RECEIVED,
        TURN_NOTIFICATION;
    }

    public Notification(messageType type, String senderName){
        this.type = type;
        this.messageBody = this.generateMessageTemplate(senderName);
    }

    //TODO: Add a try/catch for messageType validity
    private String generateMessageTemplate(String senderName){
        switch(type){
            case INVITE_PENDING: return "Invite from " + senderName;
            case INVITE_REJECTED: return "Invite to " + senderName + " rejected";
            case INVITE_RECEIVED: return "Invite to " + senderName + " accepted";
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
