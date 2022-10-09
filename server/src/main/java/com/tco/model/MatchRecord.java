package com.tco.model;

public class MatchRecord {
   private MoveLog moveLog;
   private Player winner;
   private Player loser;
   
   public MatchRecord(MoveLog moveLog, Player winner, Player loser){
        this.moveLog = moveLog;
        this.winner = winner;
        this.loser = loser;
   }

   public Player getWinner(){
    return winner;
   }

   public Player getLoser(){
    return loser;
   }

   public String displayMoveLog(){
    return "";
   }
}
