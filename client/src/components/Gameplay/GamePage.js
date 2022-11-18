import React from 'react';

import Board from '../Gameplay/Board';

import blackPawn from '../../static/images/blackPawn.png';

export default function GamePage(props) {

  return (
    <>
    <Board boardWidth={props.boardWidth}/>
    <div className='opponent-username'>Opponent</div>
    <div className='player-captured-pieces'>
      <img src={blackPawn}/>
    </div>
    <div className='player-username'>Player</div>
    </>
  );
}