import Topbar from '../components/TopBar';
import CartTile from '../components/CartTile';
import React, {useState, useEffect } from 'react';
import axios from 'axios';
export default function Cart(props) {
  const [data, setData] = useState([]);
  const [error, setError] = useState([]);


  useEffect(() => {
    const fetchData = async () => {
      try {
        const userId=1;
        const response = await axios.post('http://localhost:8081/cart',userId, {
          headers: {
            'Content-Type': 'application/json',
          },});
        setData(response.data);
        console.log(response.data)
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
            <Topbar user={props.user}/>
            <ul>
            {data.map(product => ( 
            <li key={product.id}><CartTile id={product.id} name={product.productName} amount={product.amount}price={product.price.toFixed(2)} onRemove={remove_item} seller={product.seller}/></li>         
          ))}
            </ul>

            <button className='checkout'>Do kasy</button>
          </div>
        
    );
  }