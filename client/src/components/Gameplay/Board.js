import React from 'react';
import { Chessboard } from 'react-chessboard';

export default function Board(props) {
  return (
    <div className="primary-board-container">
      <Chessboard id="BasicBoard" />
    </div>
  );
}
