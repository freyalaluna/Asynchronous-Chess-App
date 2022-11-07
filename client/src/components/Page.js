import React, { useEffect, useState } from 'react';
import { Collapse } from 'reactstrap';
import Header from './Header/Header';
import Board from './Gameplay/Board';

import { useToggle } from '../hooks/useToggle';
import { LOG } from '../utils/constants';
import { getOriginalServerUrl, sendAPIRequest } from '../utils/restfulAPI';
import { useAccount } from '../hooks/useAccount';

export default function Page(props) {
	// const [showAbout, toggleAbout] = useToggle(false);
	//const { places, selectedIndex, placeActions } = usePlaces();
	const [chessboardWidth, setChessboardWidth] = useState(400);
	const {account, setAccount, userID, setUserID, email, setEmail, accountActions} = useAccount();
	
	useEffect(() => {
    function handleResize() {
      const display = document.getElementsByClassName("primary-board-container")[0];
			let width = Math.min(display.offsetWidth - 20, display.offsetHeight - 20);
      setChessboardWidth(width);
    }

    window.addEventListener("resize", handleResize);
    handleResize();
    return () => window.removeEventListener("resize", handleResize);
  }, []);

	return (
		<>
		<div className='wrapper'>
			<Header />
			<Board boardWidth={chessboardWidth}/>
			<div className='opponent-username'>Opponent</div>
			<div className='player-username'>Player</div>
		</div>
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
