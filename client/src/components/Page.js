import React, { useEffect, useState } from 'react';
import { Collapse } from 'reactstrap';
import Header from './Header/Header';
import GamePage from './Gameplay/GamePage';
import Signup from './Signup';
import Login from './Login';
import { useToggle } from '../hooks/useToggle';
import { LOG } from '../utils/constants';
import { getOriginalServerUrl, sendAPIRequest } from '../utils/restfulAPI';
import { useAccount } from '../hooks/useAccount';
import { useMove } from '../hooks/useMove';

export default function Page(props) {
	// const [showAbout, toggleAbout] = useToggle(false);
	//const { places, selectedIndex, placeActions } = usePlaces();

	const [chessboardWidth, setChessboardWidth] = useState(400);
	const {account, setAccount, userID, setUserID, email, setEmail, accountActions} = useAccount();
	const {moveActions} = useMove();
	const [showLogin, toggleLogin] = useToggle(false);

    function handleResize() {
		const display = document.getElementsByClassName("primary-board-container")[0];
		let width = Math.min(display.offsetWidth - 20, display.offsetHeight - 20);
		setChessboardWidth(Math.max(width,0));
	}
	
	useEffect(() => {
    	window.addEventListener("resize", handleResize);
    	handleResize();
 		return () => window.removeEventListener("resize", handleResize);
  	}, []);

	return (
		<>
			<Header userID={userID} toggleLogin={toggleLogin} setUserID={setUserID}/>

      		<Signup 
				accountActions={accountActions} 
				setUserID={setUserID} 
				setEmail={setEmail}
				visible={userID == 0 && !showLogin}
				toggleLogin={toggleLogin}
			/>
			<Login 
				accountActions={accountActions} 
				setUserID={setUserID} 
				visible={userID == 0 && showLogin}
				toggleLogin={toggleLogin}
				handleResize={handleResize}
			/>
			<GamePage 
				account={account} 
				boardWidth={chessboardWidth}
				visible={userID != 0}
        moveActions={moveActions}
			/>

		</>
	);
}
