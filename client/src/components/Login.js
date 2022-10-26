import React, {useEffect, useRef, useState} from 'react';
import '../static/styles/login.css'
//import { BrowserRouter, Route, Switch } from 'react-router-dom'; 


const Login = () => {
    const errorRef = useRef();

    const [formData, setFormData] = useState({
        username: "",
        password: ""
    })

    const [errorMessage, setErrorMessage] = useState('');
    const [success, setSuccess] = useState(false);

    const handleLogin = e => {
        e.preventDefault();
        console.log(formData);
        /*API Calls, validation*/
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
                <h2>Login Successful!</h2>
                {/*DOM router to take us to home page*/}
            </div>
        ) : (
            <div className='auth-wrapper'>
                <p ref={errorRef} className={errorMessage ? "loginError" : "offscreen"}>
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
        )}
        </>
    )
}


export default Login