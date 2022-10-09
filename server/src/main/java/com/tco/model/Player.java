package com.tco.model;

import java.util.ArrayList;
import com.tco.model.Account;
import com.tco.model.Piece;


public class Player {
    private boolean color;
    private ArrayList<Piece> capturedPieces; 
    private ArrayList<Piece> remainingPieces; 
    private int accountID;
    private String username;
    
    public Player(){
        this.color= color;
        this.capturedPieces = new ArrayList<Piece>();
        this.remainingPieces = new ArrayList<Piece>();
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
