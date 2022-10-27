import React, {useEffect, useState} from 'react';
import Header from './Header/Header';
import { sendAPIRequest, getOriginalServerUrl } from '../utils/restfulAPI';
//import {sendAccountRequest} from '../hooks/useAccount';
import '../static/styles/login.css';
//import { Link, Redirect } from 'react-router-dom'; 

var bcrypt = require('bcryptjs');
var salt = bcrypt.genSaltSync(10);

const Signup = () => {

    const [formData, setFormData] = useState({
        email: "",
        username: "",
        password: ""
    })

    const [confPassword, setConfPassword] = useState('');

    const [errorMessage, setErrorMessage] = useState('');

    const validateEmail = (str = "") => {
        if(!str.includes("@")){
            setErrorMessage(errorMessage + 'Email must be of form \'name@domain.extension\'\n')
            return false
        }
        return true
    }

    const validateUsername = (str = "") => {
        if(str.length === 0){
            setErrorMessage(errorMessage+'Username field must be filled out\n');
            return false;
        }
        return true;
    }

    const validatePass = (pass, confirmPass) => {
        if(pass !== confirmPass){
            setErrorMessage(errorMessage+'Passwords must match\n');
            return false;
        }
        return true;
    }

    const handleChange = e => {
        setFormData({
            ...formData,
            [e.target.name] : e.target.value
        })
    };

    const handlePWChange = e => {
        setConfPassword(e.target.value);
    }

    const handleSignin = async(e) => {
        e.preventDefault();
        setErrorMessage("");

        //This isn't very DRY
        var success = true;
        if(!validateEmail(formData.email)) {success=false;}
        if(!validateUsername(formData.username)){success=false};
        if(!validatePass(formData.password, confPassword)){success=false;}

        if(!success){
            return
        }

        var hash = bcrypt.hashSync(formData.password, salt);
        formData.password = hash;

        console.log(formData, confPassword);

        try{
            const accountResponse = await sendAPIRequest({
                requestType: "registerAccount", 
                username: formData.username, 
                email: formData.email, 
                password: formData.password}, 
                getOriginalServerUrl()); 
        } catch (e){
            setErrorMessage("Information is already registered to an account");
            return;
        }
        //<Redirect to="/Board" />
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
                                onChange={handleChange}
                                required/> 
                        </label>
                    </div>

                    <div className='input-field'>
                        <label>
                            Username:
                            <input type='text' 
                                name='username'
                                placeholder='Enter username'
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
                                onChange={handleChange} 
                                required/>
                        </label>
                    </div>
                    <div className='input-field'>
                        <label>
                            Confirm Password:
                            <input 
                                type='password' 
                                name='confPassword'
                                placeholder='Re-enter password'
                                onChange={handlePWChange}
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
                <p className='signup-subscript'>Already have an account? {/*<Link to="/Login" className='subscript-link'>Click here to log in.</Link>*/}
                </p>
            </div>
        </>
    )
}


export default Signup