// import MatchHistory.java
// inport Inbox.java
public class Account {
    public String username;
    private String email;
    public int accountID;

    //needs to return type MatchHistory
    public String getMatchHistory() {
        return "MatchHistory";
    }

    //needs to return type Inbox
    public String getInbox(){
        return "Inbox";
    }
}
