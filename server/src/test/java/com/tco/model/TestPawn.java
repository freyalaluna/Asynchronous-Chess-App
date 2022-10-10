package com.tco.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TestPawn {
    private static Board board = new Board();
    private static char[][] boardState;

    @BeforeEach
    public void resetBoardState() {
        boardState = board.getBoardState();
    }
}
