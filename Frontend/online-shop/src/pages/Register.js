import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function Register() {
  const [data, setData] = useState(null);
  const [error, setError] = useState('');
  const [formData, setFormData] = useState({
    login: '',
    email: '', 
    firstName: '',
    lastName: '',
    password: '',
    password2: '',
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
      const response = await axios.post('http://localhost:8081/register',formData);
      setData(response.data);
            
    } catch (error) {
      setError(error.message);
    }
    
    document.body.style.cursor = 'default'
  };

  return (
    <div className='register-login'>
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="username">Username:</label>
        <input 
          type="text"
          id="username"
          name="login"
          value={formData.username}
          onChange={handleChange}
          placeholder='username'
          required
        />

        <div className='clearfix'></div>

        <p>Can contain signs: a-z, A-Z, 0-9, .-_</p>
        <p>Must be 4-20 characters long</p>
        <div className='line'></div>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          placeholder='email'
          required
        />

        <br />
        <div className='line'></div>
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          id="name"
          name="firstName"
          value={formData.name}
          onChange={handleChange}
          placeholder='first name'
          required
        />

        <br />
        <div className='line'></div>
        <label htmlFor="surname">Surname:</label>
        <input
          type="text"
          id="surname"
          name="lastName"
          value={formData.surname}
          onChange={handleChange}
          placeholder='last name'
          required
        />

        <br />
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
        <p>Must contain at least one of each these signs: a-z, A-Z, 0-9, .-_</p>	
		    <p>Must be 8-32 characters long</p>
        <br />
        <div className='line'></div>
        <label htmlFor="repeatPassword">Repeat Password:</label>
        <input
          type="password"
          id="repeatPassword"
          name="password2"
          value={formData.repeatPassword}
          onChange={handleChange}
          placeholder='repeat password'
          required
        />

        <br />
        <div className='line'></div>
        <div className='clearfix'></div>
        <button  type="submit">Register</button>
      </form>

      <div className='errorMessage'>{data && data.message}</div>
      <br/>
      <p>Masz już konto?</p>
      <button onClick={()=>{navigate('/login')}} >Zaloguj</button>
    </div>
  );
}
