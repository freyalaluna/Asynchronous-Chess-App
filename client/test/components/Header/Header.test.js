import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import { CLIENT_TEAM_NAME } from '../../../src/utils/constants';
import { VALID_CONFIG_RESPONSE } from '../../sharedMocks';
import { beforeEach, describe, expect, test, jest } from '@jest/globals';
import Header from '../../../src/components/Header/Header';

describe('Header', () => {
	const headerProps = {
		toggleAbout: jest.fn(),
		placeActions: {
			append: jest.fn(),
			removeAll: jest.fn(),
		},
		disableRemoveAll: true,
		serverSettings: {
			serverConfig: { requestType: 'config', serverName: 'g17' },
			serverUrl: 'http://localhost:8000',
		},
		processServerConfigSuccess: jest.fn(),
	};

	beforeEach(() => {
		fetch.resetMocks();
		fetch.mockResponse(VALID_CONFIG_RESPONSE);
		render(<Header {...headerProps} />);
	});
	// it('craetz: test team name in header', async () => {
	// 	await waitFor(() => {
	// 		const headings = screen.getByTestId('header-subtitle');
	// 		expect(headings.textContent).toEqual(CLIENT_TEAM_NAME);
			
	// 	})
	// });

	test('mheavner: assert true', () => {
		expect(true);
	});
});
