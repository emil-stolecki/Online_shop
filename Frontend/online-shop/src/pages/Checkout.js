import React, {useState, useEffect } from 'react';
import Topbar from '../components/TopBar';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
export default function Checkout() {
  const [data, setData] = useState([]);
  const [error, setError] = useState([]);
  const [totalCost,setTotalcost] = useState(0);
  const navigate = useNavigate();
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8081/cart', {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`
          },});
        setData(response.data);
        let totCost =0
        response.data.map(product => (totCost=totCost+(product.amount*product.price)))
        setTotalcost(totCost)
      } catch (error) {
        setError(error.message);
      }
    };
    fetchData();
  }, []); 

async function pay(){
  try {
    const response = await axios.get('http://localhost:8081/checkout', {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      },});
      console.log(response.data)
      if(response.success==true) navigate('/succesfully-paid')
  } catch (error) {
    setError(error);
    
  }

}
    return (    
          <div>
            <Topbar parent='cart'/>
            <div style={{width:'76vw'}}>
            <div className='receipt'>
            <ol>
              {data.map(product => (         
              <li key={product.id}>
                {product.productName} <b>{product.price.toFixed(2)}</b>
              </li>         
          ))}
            </ol>
            <div className='float-bottom'>
              <div className='line2'></div>
              <h2>Razem: {totalCost.toFixed(2)}</h2>
            </div>
            </div>
            <button className='pay' onClick={pay}>Kupuję i płacę</button>
            </div>
            <div className='clearfix'></div>
            {error&&error}
          </div>
        
    );
  }