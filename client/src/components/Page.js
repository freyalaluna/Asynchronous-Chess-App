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
			<Header />

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
// <MainContentArea
// showAbout={showAbout}
// toggleAbout={toggleAbout}
// tripName={tripName}
// />
// function MainContentArea(props) {
// 	return (
// 		<div className='body'>
// 		</div>
// 	);
// }

// function useServerSettings(showMessage) {
// 	const [serverUrl, setServerUrl] = useState(getOriginalServerUrl());
// 	const [serverConfig, setServerConfig] = useState(null);

// 	useEffect(() => {
// 		sendConfigRequest();
// 	}, []);

// 	function processServerConfigSuccess(config, url) {
// 		LOG.info('Switching to Server:', url);
// 		setServerConfig(config);
// 		setServerUrl(url);
// 	}

// 	async function sendConfigRequest() {
// 		const configResponse = await sendAPIRequest({ requestType: 'config' },serverUrl);
// 		if (configResponse) {
// 			processServerConfigSuccess(configResponse, serverUrl);
// 		} else {
// 			setServerConfig(null);
// 			showMessage(`Config request to ${serverUrl} failed. Check the log for more details.`, 'error');
// 		}
// 	}

// 	return [{ serverUrl: serverUrl, serverConfig: serverConfig }, processServerConfigSuccess,];
// }
