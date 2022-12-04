import React from 'react';
import { useState } from 'react';
import { Chess } from 'chess.js';
import { Alert, Button } from 'reactstrap';
import { FiFlag } from 'react-icons/fi'
import { BsInfoLg } from 'react-icons/bs'
import { Chessboard } from 'react-chessboard';

function useForceUpdate(){
  const [value, setValue] = useState(0); // integer state
  return () => setValue(value => value + 1); // update state to force render
}

export default function Board(props) {
  const [game, setGame] = useState(new Chess());
  const [showAlert, setShowAlert] = useState(false);
  const [showGameWon, setShowGameWon] = useState(false);
  const [showGameLost, setShowGameLost] = useState(false);
  const [showInfo, setShowInfo] = useState(false);
  const forceUpdate = useForceUpdate();

  let customStyle = { borderRadius: '5px', boxShadow: '0 5px 15px rgba(0, 0, 0, 0.5 '}

  

  async function onDrop(sourceSquare, targetSquare, piece) {
    //check if correct color piece moved
    if (game.turn() != piece.charAt(0)) {
      setShowAlert(true);
      setTimeout(() => { setShowAlert(false); }, 1500);
      return null;
    }

    //call api request
    let response = await props.moveActions.sendMoveRequest(sourceSquare, targetSquare, piece, game.fen())
    if (response[0]) {
      game.move({
        from: sourceSquare,
        to: targetSquare
      }, { sloppy: true });
      forceUpdate();
      if (response[1]) {
        if (game.turn() == 'b') {
          setShowGameWon(true);
        } else {
          setShowGameLost(true);
        }
      }

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
      <div>
        <InfoButton setShowInfo={setShowInfo}/>
        <br/>
        <ConcedeButton setShowGameLost={setShowGameLost}/>
      </div>
      <WarningAlert className="wrong-turn-warning" showAlert={showAlert}/>
      <GameInfoAlert
        className="game-over-alert"
        showInfo={showInfo}
        setShowInfo={setShowInfo}
      />
      <GameWonAlert
        className="game-over-alert"
        showAlert={showGameWon}
        setShowGameOver={setShowGameWon}
        game={game}
        setShowGame={props.setShowGame}
        setShowProfile={props.setShowProfile}
      />
      <GameLostAlert
        className="game-over-alert"
        showAlert={showGameLost}
        setShowGameOver={setShowGameLost}
        game={game}
        setShowGame={props.setShowGame}
        setShowProfile={props.setShowProfile}
      />
    </div>
  );
}

export function InfoButton(props) {
  return (
    <div className="infoButton">
      <Button color="primary" size="sm" onClick={() => {
        props.setShowInfo(true);
      }}>
        <BsInfoLg/>
      </Button>
    </div>
  );
}

export function ConcedeButton(props) {
  return (
    <div className="concedeButton">
      <Button color="warning" size="sm" onClick={() => {
        props.setShowGameLost(true);
      }}>
        <FiFlag/>
      </Button>
    </div>
  );
}

export function GameInfoAlert(props) {
  const onDismiss = () => props.setShowInfo(false);
  return (
    <Alert color="info" fade={false} isOpen={props.showInfo} toggle={onDismiss}>
      <h5>
        Extinction Chess Rules
      </h5>
      <hr/>
      <p>All the same rules as regular chess apply, except:</p>
      <ul>
        <li>Check and checkmate no longer exist</li>
        <li>You win by capturing all of a particular type of piece from your opponent</li>
        <li>The king is no more protected than any other piece</li>
      </ul>
    </Alert>
  )
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

export function GameWonAlert(props) {
  if (props.showAlert) {
    return (
      <div className="backgroundBlocker">
      <Alert color="success" fade={false}>
        You won!<br/>Congratulations 😄<br/>
        <Button className="postGameButton" color="primary" onClick={() => {
          props.game.reset();
          props.setShowGame(false);
          props.setShowGameOver(false);
          props.setShowProfile(true);
        }}>
          Back Home
        </Button>
      </Alert></div>
    )
  }
  return null;
}

export function GameLostAlert(props) {
  if (props.showAlert) {
    return (
      <div className="backgroundBlocker">
      <Alert color="danger" fade={false}>
        You lost!<br/>Better luck next time 🤕<br/>
        <Button className="postGameButton" color="danger" onClick={() => {
          props.game.reset();
          props.setShowGame(false);
          props.setShowGameOver(false);
          props.setShowProfile(true);
        }}>
          Back Home
        </Button>
      </Alert></div>
    )
  }
  return null;
}
