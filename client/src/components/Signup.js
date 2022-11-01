import React, {useEffect, useRef, useState} from 'react';
import '../static/styles/login.css'
//import { BrowserRouter, Route, Switch } from 'react-router-dom'; 

//const Email_Scheme = /^[a-zA-Z0-9]{1,}@[a-zA-Z]{1,}/;

const Signup = () => {
    const errorRef = useRef();

    const [formData, setFormData] = useState({
        email: "",
        username: "",
        password: ""
    })

    const [errorMessage, setErrorMessage] = useState('');
    const [success, setSuccess] = useState(false);

    /*const submitSignin = async (e) => {
        e.preventDefault();
        if(!Email_Scheme.test(email)){
            setErrorMessage('Email must be of form \'name@domain.extension\'');
            return
        }
        console.log(user, email, password);
        const response = await sendAPIRequest({
            method: 'GET'
            data: email, username
        }, getOriginalServerURL);
        //Fetch data from API call in try/catch
        //If email or username are already in db, set errorMessage accordingly
        //Otherwise, display success and make an API call to insert new user data into db
        
    }*/

    const handleSignin = e => {
        e.preventDefault();
        console.log(formData);
    };

    const handleChange = e => {
        setFormData({
            ...formData,
            [e.target.name] : e.target.value
        })
    };

    return(
        <>
        {success ? (
            <div>
                <h2>Signup Successful!</h2>
                {/*DOM router to take us to home page*/}
            </div>
        ) : (
            <div className='auth-wrapper'>
                <p ref={errorRef} className={errorMessage ? "signinError" : "offscreen"}>
                    {errorMessage}
                </p>
                <h2>Sign In</h2>
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
                <p className='signup-subscript'>Already have an account? <a className='subscript-link' href='#'>Click here to log in.</a>
                </p>
            </div>
        )}
        </>
    )
}


export default Signup