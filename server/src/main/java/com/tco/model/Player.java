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
    
    public Player(int accountID, String username, boolean color){
        this.accountID = accountID;
        this.username = username;
        this.color = color;
        this.capturedPieces = new ArrayList<Piece>();
        this.remainingPieces = new ArrayList<Piece>();
    };

    // Default constructor for testing
    public Player() {
        this.accountID = 1;
        this.username = "User";
        this.color = true;
        this.capturedPieces = new ArrayList<Piece>();
        this.remainingPieces = new ArrayList<Piece>();
    }

    public int getAccountID(){
        return accountID;
    }

    public String getUsername(){
        return username;
    }

    public boolean getColor() {
        return color;
    }
    
    
}
