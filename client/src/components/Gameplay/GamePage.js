import React from 'react';

import Board from '../Gameplay/Board';

export default function GamePage(props) {

  return (
    <>
    <Board boardWidth={props.boardWidth}/>
    <div className='opponent-username'>Opponent</div>
    <div className='player-username'>{props.account}</div>
    </>
  );
}