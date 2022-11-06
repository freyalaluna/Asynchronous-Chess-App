import React from 'react';
import { Chessboard } from 'react-chessboard';

export default function Board(props) {
  let customStyle = { borderRadius: '5px', boxShadow: '0 5px 15px rgba(0, 0, 0, 0.5 '}
  return (
    <div className="primary-board-container">
      <Chessboard id="BasicBoard" boardWidth={props.boardWidth} customBoardStyle={customStyle}/>
    </div>
  );
}