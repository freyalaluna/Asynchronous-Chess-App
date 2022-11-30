package com.tco.model;

import java.time.LocalDateTime;

public class Match {
    private Player player1;
    private Player player2;
    private Board board;
    private MoveLog moveLog;
    private MatchRecord matchRecord;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String captured ="";

    public Match(Player player1, Player player2, int matchID)
    {
        this.player1 = player1;
        this.player2 = player2;
        board = new Board(player1, player2, matchID);
        moveLog = new MoveLog();
        startTime = LocalDateTime.now();
    }

    public int getCurrentPlayer()
    {
        return 0;
    }

    public void addMatchToHistory(Player player)
    {
        return;
    }

    public void generateInvite(Player player)
    {
        return;
    }

    public void sendTurnNotification(Player player)
    {
        return;
    }

    public void createMatchRecord()
    {
        return;
    }

    public void appendToCaptured(String piece)
    {
        captured += piece;
    }

    public String getCaptured()
    {
        return captured;
    }
}


