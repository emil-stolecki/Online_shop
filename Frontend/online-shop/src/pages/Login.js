import React, { useState } from 'react';
import axios from 'axios';
export default function Login(props) {



  const [data, setData] = useState(null);
  const [error, setError] = useState('');
  const [formData, setFormData] = useState({
    login: '',
    password: ''
  });
  const handleChange = (e) => {
      const { name, value } = e.target;
      setFormData((prevData) => ({
        ...prevData,
        [name]: value,
      }));
    };
  const handleSubmit = async (e) => {
      e.preventDefault();    
      
      try {
        
        const response = await axios.post('http://localhost:8081/login',formData,{withCredentials: true,headers: {
          'Content-Type': 'application/json',
        },});
        setData(response.data);

        if(response.data.successfull){
          localStorage.setItem('userId', response.data.user.id);
          localStorage.setItem('userName', response.data.user.login);
          localStorage.setItem('userEmail', response.data.user.email);
        }
              
      } catch (error) {
        setError(error.message);
      }
      
  
    };
    return (    
          <div className='register-login'>
            <h2>Login</h2>
            <form  onSubmit={handleSubmit}>
                  <label htmlFor="login">Login:</label>
                  <input 
                  type="text"
                  id="login"
                  name="login"
                  value={formData.login}
                  onChange={handleChange}
                  placeholder='login'
                  required
                  />

                  <div className='clearfix'></div>
          
                  <div className='line'></div>
                  <label htmlFor="password">Password:</label>
                  <input
                  type="password"
                  id="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  placeholder='password'
                  required
                  />
                  <div className='clearfix'></div>
                  <button type="submit">Login</button>
            </form >
           <div className='errorMessage'>{error && error}</div>
          </div>    
    );
  }