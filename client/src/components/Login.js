import React, {useEffect, useRef, useState} from 'react';
import Header from './Header/Header'
import { sendAPIRequest, getOriginalServerUrl } from '../utils/restfulAPI';
import '../static/styles/login.css'
//import { BrowserRouter, Route, Switch } from 'react-router-dom'; 


const Login = () => {

    const [formData, setFormData] = useState({
        username: "",
        password: ""
    })

    const [errorMessage, setErrorMessage] = useState('');

    const handleLogin = async(e) => {
        e.preventDefault();

        setErrorMessage("");
        if(formData.username.length === 0 || formData.password.length === 0){
            setErrorMessage("All form fields must be filled out");
            return;
        }

        var hash = bcrypt.hashSync(formData.password, salt);
        formData.password = hash;
        try{
            const accountResponse = await sendAPIRequest({
                requestType: "registerAccount", 
                username: formData.username, 
                email: formData.email, 
                password: formData.password}, 
                getOriginalServerUrl()); 
        } catch (e){
            setErrorMessage("Username and/or password are invalid");
            return;
        }
    };

    const handleChange = e => {
        setFormData({
            ...formData,
            [e.target.name] : e.target.value
        })
    };

    return(
        <>
            <Header/>
            <div className='auth-wrapper'>
                <p className={errorMessage ? "loginError" : "offscreen"}>
                    {errorMessage}
                </p>
                <h2>Log In</h2>
                <form onSubmit={handleLogin}>
                    <div className='input-field'>
                        <label>
                            Username:
                            <input type='text' 
                                name='username'
                                placeholder='Enter username'
                                onChange={handleChange}
                                required/>
                        </label>
                    </div>

                    <div className='input-field'>
                        <label>
                            Password:
                            <input 
                                type='password' 
                                name='password'
                                placeholder='Enter password'
                                onChange={handleChange} 
                                required/>
                        </label>
                    </div>

                    <button
                        className='submit-button'
                        type='submit'>
                        Register
                    </button>
                </form>
                <br></br>
                <p className='signup-subscript'>Don't have an account? <a className='subscript-link' href='#'>Click here to sign up.</a>
                </p>
            </div>
        </>
    )
}


export default Login