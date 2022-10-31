import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import user from '@testing-library/user-event';
import { beforeEach, describe, expect, it, test } from '@jest/globals';
import { VALID_CONFIG_RESPONSE } from '../sharedMocks';
import { CLIENT_TEAM_NAME } from '../../src/utils/constants';
import Page from '../../src/components/Page';

describe('Page', () => {
	beforeEach(() => {
		fetch.resetMocks();
		fetch.mockResponse(VALID_CONFIG_RESPONSE);
		render(<Page />);
	});

	it('craetz: test team name in header', async () => {
		await waitFor(() => {
			const headings = screen.getByTestId('header-subtitle');
			expect(headings.textContent).toEqual(CLIENT_TEAM_NAME);
		})
	});
/*
	test('base: closes map and opens about when About button is clicked', async () => {
		const collapse = screen.getByTestId('planner-collapse');
		expect(collapse.classList.contains('show')).toBe(true);

		const menuToggle = screen.getByTestId('menu-toggle');
		await waitFor(() => user.click(menuToggle));

		const toggleAboutButton = screen.getByTestId('about-button');
		user.click(toggleAboutButton);

		await waitFor(() => {
			expect(collapse.classList.contains('show')).toBe(false);
		});
	});

	test('base: Changes to close button on About page', async () => {
		const menuToggle = screen.getByTestId('menu-toggle');
		await waitFor(() => user.click(menuToggle));
		const aboutButton = screen.getByTestId('about-button');
		await waitFor(() => user.click(aboutButton));
		const closeAboutButton = screen.getByTestId('close-about-button');
		expect(closeAboutButton).toBeTruthy();
		await waitFor(() => user.click(closeAboutButton));
		const collapse = screen.getByTestId('planner-collapse');
		await waitFor(() => expect(collapse.classList.contains('show')).toBe(true));
		expect(closeAboutButton.classList.contains('show')).toBe(false);
	});*/

	test('mheavner: assert true', () => {
		expect(true);
	});
});
