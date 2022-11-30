import React from 'react';

import Board from '../Gameplay/Board';

export default function GamePage(props) {

  return (
    <div style={props.visible?null:{display:"none"}}>
      <Board boardWidth={props.boardWidth} moveActions={props.moveActions}/>
      <div className='opponent-username'>Opponent</div>
      <div className='player-username' data-testid='player-username'>{props.account}</div>
    </div>
  );
}