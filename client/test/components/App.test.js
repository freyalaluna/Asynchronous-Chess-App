import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import { beforeEach, describe, test } from '@jest/globals';
import { CLIENT_TEAM_NAME, APP_NAME } from '../../src/utils/constants';
import App from '../../src/components/App';

describe('App', () => {
    beforeEach(() => {
        fetch.resetMocks();
        render(<App />);
    });

    it('victor45: test team name in header', async () => {
      await waitFor(() => {
        const headings = screen.getByTestId('header-subtitle');
        expect(headings.textContent).toEqual(CLIENT_TEAM_NAME);
      })
    });

    it('victor45: test app name in header', async () => {
      await waitFor(() => {
        const headings = screen.getByTestId('header-title');
        expect(headings.textContent).toEqual(APP_NAME);
      })
    });

    test('mheavner: assert true', () => {
		expect(true);
	});
});

