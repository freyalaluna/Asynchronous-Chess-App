import React from 'react';
import { Container, Button, Menu } from 'reactstrap';
import { APP_NAME, CLIENT_TEAM_NAME } from '../../utils/constants';
import { useToggle } from '../../hooks/useToggle';
import { IoMdMenu } from 'react-icons/io';

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
					<HeaderButton />
				</div>
			</Container>
		</div>
	);
}

function HeaderButton(props) {
	return (
		<React.Fragment>
			<Button
				color='primary'
			>
				<IoMdMenu size={32} />
			</Button>
		</React.Fragment>
	);
}
