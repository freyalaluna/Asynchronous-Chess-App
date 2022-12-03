import React from 'react';

import PlayerInfo from '../Profile/PlayerInfo';
import ProfileActions from '../Profile/ProfileActions';
import ProfileActiveGames from '../Profile/ProfileActiveGames';

export default function ProfilePage(props) {

  return (
    <div style={props.visible?null:{display:"none"}}>
      <PlayerInfo email={props.email} account={props.account} accountActions={props.accountActions} setShowProfile={props.setShowProfile} />
      <ProfileActions setShowProfile={props.setShowProfile} setShowGame={props.setShowGame} />
    </div>
  );
}
