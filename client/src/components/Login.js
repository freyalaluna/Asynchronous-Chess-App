import React, {useEffect, useState} from 'react';
import '../static/styles/login.css'
//import { BrowserRouter, Route, Switch } from 'react-router-dom'; 
//fturner: Don't know if we want to work with react-router-dom extension yet

/*
const Login = () => {

}
*/

export default function Login(){
    <div className='wrapper'>
        <h2>Sign In</h2>
        <form>
            <label>
                Username:
                <input type='text' name='username' placeholder='Enter username' required/>
            </label>
            <label>
                Password:
                <input type='password' name='password' placeholder='Enter password' required/>
            </label>
            <button type='submit'>
                Submit
            </button>
        </form>
        <p className='signup-subscript'>Don't have an account? 
            <a href='#'>Click here to sign up.</a>
        </p>
    </div>
}

/*fturner: TODO: Create helper functions/js files to:
 - After form submission, make a POST request to the server using an API (Axios, Spring Boot, ???)
 - encrypt password before login info is sent to server (bcryptjs)
 - generate a user token to be stored in local/session storage/cookies, which ensures the user stays logged in until it expires
 - Anything else? */