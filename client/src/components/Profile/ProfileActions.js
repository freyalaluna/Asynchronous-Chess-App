import React from 'react';
import { useState } from 'react';
import { Button } from 'reactstrap';

export default function PlayerInfo(props) {
  return (
    <Button className="playGameButton" color="primary" size="lg" onClick={async () => {
        props.setShowGame(true);
        props.setShowProfile(false);
        await new Promise(r => setTimeout(r, 100));
        window.dispatchEvent(new Event('resize'));
        //above code triggers a resize event, which causes the board to load
        //board will not load on its own for some reason
    }}>
      Play Chess!
    </Button>
  );
}
