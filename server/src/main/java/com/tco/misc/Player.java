import java.util.ArrayList;
import com.tco.misc.Account;
import com.tco.misc.Pieces;


public class Player {
    private boolean color;
    private ArrayList<Pieces> capturedPieces; 
    private ArrayList<Pieces> remainingPieces; 
    private int accountID;
    private String username;
    
    public Player(){
        this.color= color;
        this.capturedPieces = new ArrayList<Pieces>();
        this.remainingPieces = new ArrayList<Pieces>();
        this.accountID = accountID;
        this.username = username;
    };

    public int getAccountID(){
        return accountID;
    }

    public String getUsername(){
        return username;
    }
    
    
}
