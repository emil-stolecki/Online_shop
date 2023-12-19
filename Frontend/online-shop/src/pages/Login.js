import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
export default function Login(props) {



  const [data, setData] = useState(null);
  const [error, setError] = useState('');
  const [formData, setFormData] = useState({
    login: '',
    password: ''
  });
  const navigate = useNavigate();
  const handleChange = (e) => {
      const { name, value } = e.target;
      setFormData((prevData) => ({
        ...prevData,
        [name]: value,
      }));
    };
  const handleSubmit = async (e) => {
      e.preventDefault();  
      document.body.style.cursor = 'wait'  
      try {
        const response = await axios.post('http://localhost:8081/login',formData,{withCredentials: true,headers: {
          'Content-Type': 'application/json',
        },});
        setData(response.data);
        if(response.data.successfull){
          localStorage.setItem('token',response.data.token)
          localStorage.setItem('id',response.data.id)
          navigate('/home')
        }
              
      } catch (error) {
        setError(error.message);
      }
      document.body.style.cursor = 'default'
  
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
           <div className='errorMessage'>{data && !data.successfull &&"Błędny login lub hasło"}</div>
            <p>Nie masz jeszcze konta??</p>
            <button onClick={()=>{navigate('/register')}} >Zarejestruj</button>
          </div>    
    );
  }