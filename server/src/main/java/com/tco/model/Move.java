package com.tco.model;

import java.time.LocalDateTime;
import com.tco.model.Piece;
import com.tco.model.Square;

public final class Move {
    private Piece piece;
    private Square startCoord;
    private Square endCoord;
    private boolean capture;
    private LocalDateTime timestamp;
    
    public Move(Piece piece, Square startCoord, Square endCoord, boolean capture, LocalDateTime timestamp) {
        this.piece = piece;
        this.startCoord = startCoord;
        this.endCoord = endCoord;
        this.capture = capture;
        this.timestamp = timestamp;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Square getStartCoord() {
        return this.startCoord;
    }

    public Square getEndCoord() {
        return this.endCoord;
    }

    public boolean captured() {
        return this.capture;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
}