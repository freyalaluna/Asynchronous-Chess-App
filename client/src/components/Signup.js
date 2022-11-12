import React, {useEffect, useState} from 'react';
import Header from './Header/Header';
import {LOG} from '../utils/constants'
import '../static/styles/login.css';

export default function Signup(props){

    const [formData, setFormData] = useState({
        email: "",
        username: "",
        password: "",
        confPassword : ""
    })

    const [errorMessage, setErrorMessage] = useState('');

    const handleChange = e => {
        setFormData({
            ...formData,
            [e.target.name] : e.target.value
        })
    };

    const handleSignin = async(e) => {
        e.preventDefault();
        setErrorMessage("");

        var success = true;
        if(!validateEmail(formData.email)) {success=false;}
        if(!validateUsername(formData.username)){success=false};
        if(!validatePass(formData.password, formData.confPassword)){success=false;}

        if(!success){
            e.target.reset();
            setFormData({
                ...formData,
                email : "",
                username : "",
                password : "",
                confPassword : ""
            })
            return
        }

        var responseID = 1;
        if (process.env.NODE_ENV !== 'test'){
            responseID = await props.accountActions.sendAccountRequest(formData.username, formData.password, formData.email);
        }

        if(responseID != -1){
            console.log("Success");
            console.log("UserID: " + responseID);
            props.setUserID(responseID);
            props.setEmail(formData.email);
        } else {
            console.log("Failure");
        }
    };

    return(
        <>
            <Header/>
            <div className='auth-wrapper'>
                <p className={errorMessage ? "signinError" : "offscreen"}>
                    {errorMessage}
                </p>
                <h2>Sign Up</h2>
                <form onSubmit={handleSignin}>
                    <div className='input-field'>
                        <label>
                            Email:
                            <input type='text' 
                                name='email' 
                                placeholder='Enter email'
                                value={formData.email}
                                onChange={handleChange}
                                /> 
                        </label>
                    </div>

                    <div className='input-field'>
                        <label>
                            Username:
                            <input type='text' 
                                name='username'
                                placeholder='Enter username'
                                value={formData.username}
                                onChange={handleChange}
                                />
                        </label>
                    </div>

                    <div className='input-field'>
                        <label>
                            Password:
                            <input 
                                type='password' 
                                name='password'
                                placeholder='Enter password'
                                value={formData.password}
                                onChange={handleChange} 
                                />
                        </label>
                    </div>
                    <div className='input-field'>
                        <label>
                            Confirm Password:
                            <input 
                                type='password' 
                                name='confPassword'
                                placeholder='Re-enter password'
                                value={formData.confPassword}
                                onChange={handleChange}
                                />
                        </label>
                    </div>

                    <button
                        className='submit-button'
                        type='submit'>
                        Register
                    </button>
                </form>
                <br></br>
                <p className='signup-subscript'>Already have an account? <a className='subscript-link' href='#'>Click here to sign up.</a>
                </p>
            </div>
        </>
    )
}

export const validateEmail = (str = "") => {
    if(!str.includes("@")){
        //setErrorMessage(errorMessage + 'Email must be of form \'name@domain.extension\'\n')
        LOG.error("Email must be of form \'name@domain.extension\'\n'");
        return false
    }
    return true
}

export const validateUsername = (str = "") => {
    if(str.length === 0){
        //setErrorMessage(errorMessage+'Username field must be filled out\n');
        LOG.error("Username field must be filled out\n");
        return false;
    }
    return true;
}

export const validatePass = (pass, confirmPass) => {
    if(pass !== confirmPass){
        //setErrorMessage(errorMessage+'Passwords must match\n');
        LOG.error("Passwords must match\n");
        return false;
    }
    return true;
}
