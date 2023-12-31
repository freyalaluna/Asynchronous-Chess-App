import React from 'react';
import { Container, DropdownMenu, DropdownToggle, UncontrolledDropdown, DropdownItem } from 'reactstrap';
import { APP_NAME, CLIENT_TEAM_NAME } from '../../utils/constants';
import { useToggle } from '../../hooks/useToggle';
import { IoMdMenu } from 'react-icons/io';

export default function Header(props) {
	return (
		<React.Fragment>
			<HeaderContents userID={props.userID} setShowLogin={props.setShowLogin} setShowProfile={props.setShowProfile} setShowGame={props.setShowGame} setUserID={props.setUserID} />
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
					{props.userID != 0 ? <HeaderDropdown setShowLogin={props.setShowLogin} setShowProfile={props.setShowProfile} setShowGame={props.setShowGame} setUserID={props.setUserID} /> : null }
				</div>
			</Container>
		</div>
	);
}

function HeaderDropdown(props) {
	return (
		<Dropdown>
			<DropdownItem data-testid='show-profile-button' onClick={() => {props.setShowProfile(true); props.setShowLogin(false); props.setShowGame(false)}}>
				My Profile
			</DropdownItem>
			<DropdownItem data-testid='account-logout-button' onClick={() => {props.setUserID(0); props.setShowLogin(true); props.setShowGame(false); props.setShowProfile(false)}}>
				Log Out
			</DropdownItem>
		</Dropdown>
	);
}

function Dropdown(props) {
	return (
		<UncontrolledDropdown direction="down">
			<DropdownToggle color="primary">
				<IoMdMenu size={32} />
			</DropdownToggle>
			<DropdownMenu>
				{props.children}
			</DropdownMenu>
		</UncontrolledDropdown>
	);
}
