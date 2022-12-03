import React from 'react';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import user from '@testing-library/user-event';
import { beforeEach, describe, expect, it, test } from '@jest/globals';
import { VALID_CONFIG_RESPONSE } from '../sharedMocks';
import Login from '../../src/components/Login';
import { sendAPIRequest } from '../../src/utils/restfulAPI';

describe('Login', () => {
    
    test('fturner: test that form submission works', () => {
        const name = "fturner";
        const pass = "abcde";

        const onSubmit = jest.fn();
        render(<Login onSubmit={onSubmit}/>);
        const logSpy = jest.spyOn(console, "log");

        const nameField = screen.getByPlaceholderText("Enter username");
        const pwField = screen.getByPlaceholderText("Enter password");
        const submitButton = screen.getByPlaceholderText("Log In");

        fireEvent.change(nameField, {target: {value: name}});
        fireEvent.change(pwField, {target: {value: pass}});
        fireEvent.click(submitButton);

        expect(logSpy).toHaveBeenCalledWith("Success");
        
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