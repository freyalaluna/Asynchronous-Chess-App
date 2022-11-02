import React from 'react';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
//import { shallow } from 'enzyme';
import user from '@testing-library/user-event';
import { beforeEach, describe, expect, test } from '@jest/globals';
import { VALID_CONFIG_RESPONSE } from '../sharedMocks';
import Signup, {validateEmail, validateUsername, validatePass} from '../../src/components/Signup';
import { sendAPIRequest } from '../../src/utils/restfulAPI';

describe('Signup', () => {
    // beforeEach(() => {
    //     //wrapper = shallow(<Signup></Signup>);
    // })
    
    test('fturner: validateEmail returns false on a string without an @', () => {
        const text = "name.extension";
        const result = validateEmail(text);
        expect(result).toBe(false);
    });
    test('fturner: validateEmail returns true on a string with an @', () => {
        const text = "name@domain.extension";
        expect(validateEmail(text)).toBe(true);
    });

    test('fturner: validateUsername returns false on an empty string', () => {
        const text = "";
        expect(validateUsername(text)).toBe(false);
    })
    test('fturner: validateUsername returns true on a non-empty string', () => {
        const text = "abcde";
        expect(validateUsername(text)).toBe(true);
    })

    test('fturner: validatePass returns false when strings do not match', () => {
        const pass1 = "abcde";
        const pass2 = "efgh";
        expect(validatePass(pass1, pass2)).toBe(false);
    })
    test('fturner: validatePass returns true when strings match', () => {
        const pass1 = "abcde";
        const pass2 = "abcde";
        expect(validatePass(pass1, pass2)).toBe(true);
    })

    test('fturner: form fields are in Signup page', () => {
        const component = render(<Signup/>)

        const emailField = component.getByText("Email:");
        const userField = component.getByText("Username:");
        const passField = component.getByText("Password:");
        const passConField = component.getByText("Confirm Password:");

        expect(emailField).toBeInTheDocument();
        expect(userField).toBeInTheDocument();
        expect(passField).toBeInTheDocument();
        expect(passConField).toBeInTheDocument();
    })

    test('fturner: form fields are in Signup page, and all fields are assigned to the appropriate inputs', () => {
        const component = render(<Signup/>)
        const emailField = component.getByLabelText("Email:");
        const userField = component.getByLabelText("Username:");
        const passField = component.getByLabelText("Password:");
        const passConField = component.getByLabelText("Confirm Password:");

        expect(emailField).toBeInTheDocument();
        expect(userField).toBeInTheDocument();
        expect(passField).toBeInTheDocument();
        expect(passConField).toBeInTheDocument();

        expect(emailField.getAttribute("name")).toBe("email");
        expect(userField.getAttribute("name")).toBe("username");
        expect(passField.getAttribute("name")).toBe("password");
        expect(passConField.getAttribute("name")).toBe("confPassword");
    })

    // test('fturner: form can be submitted', () => {
    //     const mockFxn = jest.fn();
    //     const { getByRole } = render(<Signup handleSignin={mockFxn}/>);
    //     const submitButton = getByRole("button");
    //     fireEvent.submit(submitButton);
    //     expect(mockFxn).toHaveBeenCalledTimes(1);
    // })

    // describe("API Tests", () => {
    //     beforeEach(() => {
    //         fetch.resetMocks();
    //     })
    //     it('fturner: test registerAccount', async () => {
    //         const MOCK_SIGNUP_RESPONSE = {requestType: "registerAccount", username: "fturner", userID: "1"}
    //         fetch.mockResponse(JSON.stringify(MOCK_SIGNUP_RESPONSE));
    //         const response = await sendAPIRequest({
    //             requestType : "registerAccount",
    //             username : "fturner",
    //             email : "burner@gmail.com",
    //             password : "abc"
    //         }, null);
    //         expect(fetch).toBeCalledTimes(1);
    //     });
        // it('alden: test extract places from response', () => {        
        //     let placesArray = buildOptionsArray(MOCK_SIGNUP_RESPONSE);        
        //     expect(placesArray).toEqual([new Place(MOCK_FIND_RESPONSE.places[0])]);    
        // });
        // it('alden: test findPlaces returns place', async () => {        
        //     fetch.mockResponse(JSON.stringify(MOCK_FIND_RESPONSE));        
        //     const response = await findPlaces('dave', null);        
        //     expect(response).toEqual([1, [new Place(MOCK_FIND_RESPONSE.places[0])]]);    
        // });
    //})
    /*Future tests:
        - Test that error messages are properly displayed on incorrect submission
        - Feedback is appropriately displayed based on successful/failed API call
        - Page is routed on successful API call 
    */


    test('fturner: assert true', () => {
        expect(true);
    });
});