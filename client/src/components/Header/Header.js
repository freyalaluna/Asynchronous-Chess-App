import React from 'react';
import { Container, Button } from 'reactstrap';
import { APP_NAME, CLIENT_TEAM_NAME } from '../../utils/constants';
import { useToggle } from '../../hooks/useToggle';
import { IoMdClose } from 'react-icons/io';

export default function Header(props) {
	return (
		<React.Fragment>
			<HeaderContents />
		</React.Fragment>
	);
}

function HeaderContents(props) {
	return (
		<div className='full-width header vertical-center'>
			<Container>
				<div className='header-container'>
					<h1 className='header-text-container'>
						<p
							className='tco-text-upper-title'
							data-testid='header-title'
						>
							{APP_NAME}
						</p>
						<p
							className='tco-text-upper-subtitle'
							data-testid='header-subtitle'
						>
							{CLIENT_TEAM_NAME}
						</p>
					</h1>
				</div>
			</Container>
		</div>
	);
}

function HeaderButton(props) {
	return props.showAbout ? (
		<Button
			data-testid='close-about-button'
			color='primary'
			onClick={() => props.toggleAbout()}
		>
			<IoMdClose size={32} />
		</Button>
	) : (
		<Menu
			toggleAbout={props.toggleAbout}
			placeActions={props.placeActions}
			toggleAddPlace={props.toggleAddPlace}
			toggleLoadFile={props.toggleLoadFile}
			disableRemoveAll={props.disableRemoveAll}
			toggleServerSettings={props.toggleServerSettings}
		/>
	);
}

function AppModals(props) {
	return (
		<>
			<AddPlace
				isOpen={props.showAddPlace}
				toggleAddPlace={props.toggleAddPlace}
				append={props.placeActions.append}
			/>
			<ServerSettings
				isOpen={props.showServerSettings}
				toggleOpen={props.toggleServerSettings}
				processServerConfigSuccess={props.processServerConfigSuccess}
				serverSettings={props.serverSettings}
			/>
			<LoadFile
				isOpen={props.showLoadFile}
				toggleLoadFile={props.toggleLoadFile}
				placeActions={props.placeActions}
				setTripName={props.setTripName}
			/>
		</>
	);
}
