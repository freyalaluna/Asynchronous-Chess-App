import React from 'react';
import { useState } from 'react';
import { Chess } from 'chess.js';
import { Alert } from 'reactstrap';

import { Chessboard } from 'react-chessboard';

function useForceUpdate(){
  const [value, setValue] = useState(0); // integer state
  return () => setValue(value => value + 1); // update state to force render
  // An function that increment 👆🏻 the previous state like here 
  // is better than directly setting `value + 1`
}

export default function Board(props) {
  const [game, setGame] = useState(new Chess());
  const [showAlert, setShowAlert] = useState(false);
  const forceUpdate = useForceUpdate();

  let customStyle = { borderRadius: '5px', boxShadow: '0 5px 15px rgba(0, 0, 0, 0.5 '}

  

  async function onDrop(sourceSquare, targetSquare, piece) {
    //check if correct color piece moved
    var pieceIdentifier = piece.charAt(0);
    if (game.turn() != piece.charAt(0)) {
      setShowAlert(true);
      setTimeout(() => { setShowAlert(false); }, 1000);
      return null;
    }

    //call api request
    if (await props.moveActions.sendMoveRequest(sourceSquare, targetSquare, piece, game.fen())) {
      // const updatedGame = game.move({
      //   from: sourceSquare,
      //   to: targetSquare,
      //   promotion: 'q'  //always promote to queen for now, until custom promotion implemented
      // });
      // setGame(updatedGame);
      console.log("in if")
      game.move({
        from: sourceSquare,
        to: targetSquare
      }, { sloppy: true });
      forceUpdate();

      return game;
    }

    return null;
  }

  return (
    <div className="primary-board-container">
      <Chessboard
        id="BasicBoard"
        boardWidth={props.boardWidth}
        customBoardStyle={customStyle}
        position={game.fen()}
        onPieceDrop={onDrop}
      />
      <WarningAlert className="wrong-turn-warning" showAlert={showAlert}/>
    </div>
  );
}

export function WarningAlert(props) {
  if (props.showAlert) {
    return (
      <Alert color="primary" fade={false}>
        You can't move your opponent's pieces!
      </Alert>
    )
  }
  return null;
}