import Topbar from '../components/TopBar';
import Button from '../components/Button';
import axios from 'axios';
import React, {useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
export default function Profile(props) {

  const [data, setData] = useState([]);
  const [error, setError] = useState([]);
  const [date, setDate] = useState(null);
  const [mode, setMode] = useState('normal');
  const [response, setResponse] = useState(null);
  const [formData, setFormData] = useState({
    id:'',
    login: '',
    email: '', 
    firstName: '',
    lastName: '',
    password: '',
    password2: '',
    joined: '',
  });
  const[buttons, setButtons]=useState([false,false,false])
  const navigate = useNavigate();
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };
  
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8081/profile' ,{
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`
          },});
        setData(response.data);
        setDate( new Date(response.data.joined));

        setFormData(
          {
            id:response.data.id,
            login: response.data.login,
            email: response.data.email,
            firstName: response.data.firstName,
            lastName: response.data.lastName,
            password: '',
            password2: '',
            joined: response.data.joined
          }
        )

      } catch (error) {
        setError(error.message);
      }
    };
    fetchData();
  }, []); 

  function onUpdateData(){
    if(mode!=='edit')
      setMode('edit')
    else  setMode('normal')
   
  }
  function onChangePassword(){   
    if(mode!=='password')
      setMode('password')
    else  setMode('normal')
  }
  function onDeleeAccount(){
    if(mode!=='delete')
      setMode('delete')
    else  setMode('normal')
  }

  async function onUpdateDataSubmit(e){
    document.body.style.cursor = 'wait' 
    e.preventDefault();    
   
    try{
    const resp= await axios.post('http://localhost:8081/profile/edit',formData ,{
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      },});
    setResponse(resp.data);
    console.log(resp.data)
    } catch (error) {
      setError(error.message);
    }
    document.body.style.cursor = 'default'
  }
  async function onChangePasswordSubmit(e){
    document.body.style.cursor = 'wait' 
    e.preventDefault();  
    const passwords={
      password:formData.password,
      password2:formData.password2
    }
    try{
    const resp= await axios.post('http://localhost:8081/profile/change-password',passwords ,{
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      },});
    setResponse(resp.data);
  } catch (error) {
    setError(error.message);
  }
  document.body.style.cursor = 'default'
  }
  async function onDeleteSubmit(e){
    document.body.style.cursor = 'wait' 
    const resp= await axios.get('http://localhost:8081/profile/delete' ,{
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      },});
    navigate('/home')
    document.body.style.cursor = 'default' 
  }
  function getView(){
    if(mode=='normal'){
      return(<div></div>)
    }
    if(mode=='edit'){
      return(<div>
        
          <form onSubmit={onUpdateDataSubmit}>
          <input type="text" id="login" name="login" 
          value={formData.login} onChange={handleChange}placeholder='login'/>
          <br/>
          <input type="text" id="email" name="email" 
          value={formData.email} onChange={handleChange}placeholder='email'/>
          <br/>
          <input type="text" id="firstName" name="firstName" 
          value={formData.firstName} onChange={handleChange}placeholder='firstName'/>
          <br/>
          <input type="text" id="lastName" name="lastName" 
          value={formData.lastName} onChange={handleChange}placeholder='lastName'/>
          <br/>
          <br/>
          <button type='submit'>Zatwierdź</button>
          </form>
          <p>{response&&response.message}</p>
      </div>)
    }
    if(mode=='password'){
      return(<div>
        <form onSubmit={onChangePasswordSubmit}>
          <input type="password" id="password" name="password" 
          value={formData.password} onChange={handleChange}placeholder='password'/>
          <br/>
          <input type="password" id="password2" name="password2" 
          value={formData.password2} onChange={handleChange}placeholder='repeat password'/>
          
          <br/>
          <br/>
          <button type='submit'>Zatwierdź</button>
          </form>
          <p>{response&&response.message}</p>
      </div>)
    }
    if(mode=='delete'){
      return(<div>
        <button onClick={onDeleteSubmit}style={{backgroundColor: 'red'}}>Na pewno usunąć?</button>
      </div>)
    }
  }

 

  
    return (    
          <div>
           <Topbar parent='profile'/>
           <div className='clearfix'></div>
           <div className='user-edit'>
            {getView()}
           </div>    
           <div className='user_data'>
              <h6>Login</h6>
              <b>{data.login}</b>             
              <div className='clearfix'> </div>  
              <div className='line' style={{ marginLeft: '0px'}}></div>
              <h6>Email</h6>
              <b>{data.email}</b>
              <div className='clearfix'> </div> 
              <div className='line' style={{ marginLeft: '0px'}}></div> 
              <h6>Imię</h6>
              <b>{data.firstName}</b>
              <div className='clearfix'> </div>  
              <div className='line' style={{ marginLeft: '0px'}}></div>
              <h6>Nazwisko</h6>
              <b>{data.lastName}</b>
              <div className='clearfix'> </div>  
              <div className='line' style={{ marginLeft: '0px'}}></div>
              <h6>Konto Utworzone</h6>
              <b>{date&&String(date.getDate())+'-'+String(date.getMonth()+1)+'-'+String(date.getFullYear())}</b>
              <div className='clearfix'> </div>  
              <div className='line' style={{ marginLeft: '0px'}}></div>

              <Button id={0} name={'Edytuj Dane'} altName={""} function={onUpdateData} adjustState={setButtons} state={buttons}/>
              <Button id={1} name={'Zmień Hasło'} altName={""} function={onChangePassword}  adjustState={setButtons} state={buttons}/>
              <Button id={2} name={'Usuń Konto'} altName={""} function={onDeleeAccount}  adjustState={setButtons} state={buttons}/>  
           </div>            
           <div className='clearfix'></div>
          </div>
        
    );
  }