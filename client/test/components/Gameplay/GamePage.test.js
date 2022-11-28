import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import user from '@testing-library/user-event';
import { beforeEach, describe, expect, it, test } from '@jest/globals';
import GamePage from '../../../src/components/Gameplay/GamePage';

describe('GamePage', () => {
    beforeEach(() => {
        render(<GamePage account='Player'/>);
    });

    it('mheavner: Player Username shows "Player"', () => {
        const playerUsername = screen.getByTestId('player-username');
        expect(playerUsername.textContent).toEqual('Player');
    });
});