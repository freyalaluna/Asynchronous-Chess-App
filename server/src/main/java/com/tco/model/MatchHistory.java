package com.tco.model;

import java.util.ArrayList;

public class MatchHistory {
    public ArrayList<MatchRecord> history;

    public MatchHistory(){
        this.history = new ArrayList<MatchRecord>();
    }

    public int getWins(){
        return 0;
    }

    public int getLosses(){
        return 0;
    }

    public String createTimeline(){
        return "";
    }

}
