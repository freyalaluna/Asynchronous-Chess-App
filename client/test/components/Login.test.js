import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import user from '@testing-library/user-event';
import { beforeEach, describe, expect, test } from '@jest/globals';
import { VALID_CONFIG_RESPONSE } from '../sharedMocks';
import Login from '../../src/components/Login';

describe('Login', () => {
    beforeEach(() => {

    });

    /*Future tests: 
        - Passing valid and invalid (all combinations of valid/invalid username + pw) login credentials 
            to the DB and displaying accepted and rejected notifications respectively
        - Rejection when attempting to pass only a username or password to the DB
        - Page properly redirects to dashboard when login is accepted
        - Page properly redirects to sign up page when the sign up prompt is clicked
        - Page generates tokens, and token values follow the expected output from our generator
        - Any others?*/
        

    test('fturner: assert true', () => {
        expect(true);
    });
});