import Topbar from '../components/TopBar';
import CartTile from '../components/CartTile';
import React, {useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
export default function Cart(props) {
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

  function remove_item(item_id){
    setData(data.filter(i => i.id!==item_id))
  }
  
    return (    
          <div className='cart'>
            <Topbar parent='cart'/>
            <ul>
            {data.map(product => (         
            <li key={product.id}><CartTile id={product.id} name={product.productName} amount={product.amount}price={product.price} onRemove={remove_item} seller={product.seller} maxAmount={product.amountInStock} changeCost={setTotalcost} totalCost={totalCost}/></li>         
          ))}
            </ul>

            {data.length>0&&
            <div>
              <h2>Razem: {totalCost.toFixed(2)}</h2>
              <button className='checkout' onClick={navigate('/checkout')}>Do kasy</button>
              </div>
            }
            {data.length==0&&<p>Trochę tu pusto</p>}
          </div>
        
    );
  }