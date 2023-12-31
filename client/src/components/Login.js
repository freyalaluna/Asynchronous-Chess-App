import React, {useEffect, useState} from 'react';
import {LOG} from '../utils/constants'
import '../static/styles/login.css';

export default function Login(props){

    const [formData, setFormData] = useState({
        username: "",
        password: ""
    })

    const handleLogin = async(e) => {
        e.preventDefault();

        var responseID = 1;
        if (process.env.NODE_ENV !== 'test'){
            responseID = await props.accountActions.sendAccountRequest(formData.username, formData.password);
        }
        
        if(responseID != -1){
            console.log("Success");
            props.setUserID(responseID);
            props.setShowLogin(false);
            props.setShowProfile(true);
            props.handleResize();
        } else {
            console.log("Failure");
        }
        
    };

    const handleChange = e => {
        setFormData({
            ...formData,
            [e.target.name] : e.target.value
        })
    };

    return(
            <div className='auth-wrapper' style={props.visible?null:{display:"none"}}>
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
                        placeholder='Log In'
                        type='submit'>
                        Log In
                    </button>
                </form>
                <br></br>
                <p 
                    className='signup-subscript'
                >
                    Don't have an account? <a className='subscript-link' onClick={() => props.setShowLogin(false)}>Click here to sign up.</a>
                </p>
            </div>
    )
}