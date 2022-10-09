package com.tco.misc;

import java.util.ArrayList;
import com.tco.misc.Move;

public class MoveLog {
    private ArrayList<Move> moves;

    public MoveLog() {
        this.moves = new ArrayList<Move>();
    }

    public MoveLog(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public void addMove(Move move) {
        this.moves.add(move);
    }
    /*
    public String toString() {
        return "";
    }
    */
}
