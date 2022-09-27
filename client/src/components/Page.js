import React, { useEffect, useState } from 'react';
import { Collapse } from 'reactstrap';
import Header from './Header/Header';
import { useToggle } from '../hooks/useToggle';
import { LOG } from '../utils/constants';
import { getOriginalServerUrl, sendAPIRequest } from '../utils/restfulAPI';

export default function Page(props) {
	const [showAbout, toggleAbout] = useToggle(false);
	const [serverSettings, processServerConfigSuccess] = useServerSettings(
		props.showMessage
	);
	//const { places, selectedIndex, placeActions } = usePlaces();
	const [tripName, setTripName] = useState('My Trip');

	return (
		<>
			<Header
				toggleAbout={toggleAbout}
				showAbout={showAbout}
				serverSettings={serverSettings}
				processServerConfigSuccess={processServerConfigSuccess}
				setTripName={setTripName}
			/>
			<MainContentArea
				showAbout={showAbout}
				toggleAbout={toggleAbout}
				tripName={tripName}
			/>
		</>
	);
}

function MainContentArea(props) {
	return (
		<div className='body'>
		</div>
	);
}

function useServerSettings(showMessage) {
	const [serverUrl, setServerUrl] = useState(getOriginalServerUrl());
	const [serverConfig, setServerConfig] = useState(null);

	useEffect(() => {
		sendConfigRequest();
	}, []);

	function processServerConfigSuccess(config, url) {
		LOG.info('Switching to Server:', url);
		setServerConfig(config);
		setServerUrl(url);
	}

	async function sendConfigRequest() {
		const configResponse = await sendAPIRequest({ requestType: 'config' },serverUrl);
		if (configResponse) {
			processServerConfigSuccess(configResponse, serverUrl);
		} else {
			setServerConfig(null);
			showMessage(`Config request to ${serverUrl} failed. Check the log for more details.`, 'error');
		}
	}

	return [{ serverUrl: serverUrl, serverConfig: serverConfig }, processServerConfigSuccess,];
}
