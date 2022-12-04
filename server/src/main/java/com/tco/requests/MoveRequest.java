package com.tco.requests;

import com.tco.model.Board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveRequest extends Request {
  private static final transient Logger log = LoggerFactory.getLogger(MoveRequest.class);
  
  private String sourceSquare;
  private String targetSquare;
  private String piece;
  private String gameState;
  private boolean isLegalMove;
  private boolean isGameOver;

  @Override
  public void buildResponse() {
    Board board = new Board();
    boolean[] response = board.validateIncomingMove(sourceSquare, targetSquare, piece, gameState);
    this.isLegalMove = response[0];
    this.isGameOver = response[1];
  }

  //VISIBLE FOR TESTING
  public void setSourceSquare(String source) {
    this.sourceSquare = source;
  }

  public void setTargetSquare(String target) {
    this.targetSquare = target;
  }

  public void setPiece(String p) {
    this.piece = p;
  }

  public void setGameState(String fen) {
    this.gameState = fen;
  }
  
  public boolean getIsLegalMove() {
    return this.isLegalMove;
  }
  
    public boolean getIsGameOver() {
      return this.isGameOver;
    }
}
