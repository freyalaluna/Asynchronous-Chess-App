import com.tco.misc.MatchHistory;
import com.tco.misc.Inbox;
import com.tco.misc.MatchRecord;
import com.tco.misc.Notification;

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
