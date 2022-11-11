import React from 'react';
import { render, screen } from '@testing-library/react';
import { beforeEach, describe, test } from '@jest/globals';
import { LOG } from '../../src/utils/constants';
import App from '../../src/components/App';

describe('App', () => {
    beforeEach(() => {
        fetch.resetMocks();
    });

    test('victor45: render app page', () => {
        render(<App />);
    });

    test('mheavner: assert true', () => {
		expect(true);
	});
});

