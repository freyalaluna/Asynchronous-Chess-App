import React from 'react';
import { Chessboard } from 'react-chessboard';

export default function Board(props) {
  console.log("rendering chessboard");
  return (
    <div className="primary-board-container">
      <Chessboard id="BasicBoard" />
    </div>
  );
}

// function onDrop(sourceSquare, targetSquare, piece) {
//   console.log("source square:");
//   console.log(sourceSquare);
//   console.log("targte square:");
//   console.log(targetSquare);
//   console.log("piece:");
//   console.log(piece);

//   return true;
// }
