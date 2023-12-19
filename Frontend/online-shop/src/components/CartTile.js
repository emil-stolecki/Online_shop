import React, { useState, useEffect } from 'react';
import missingImage from '../images/missing_image_tile.png';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
export default function ProductPreviewTile(props) {
  const [quantity,setQuantity]=useState(props.amount);
  const navigate = useNavigate();
  const [data, setData] = useState([]);
  const [error, setError] = useState([]);

  function handleclick() {
    
    const productName = encodeURIComponent(props.name);
    const productId = encodeURIComponent(props.id);

    const url = `/product?name=${productName}&id=${productId}`;
    console.log(url)
    navigate(url); 
  };
  async function increase(){
    if(quantity<props.maxAmount){     
      const body ={
        productId:props.id,
        amount:quantity+1
      }
      props.changeCost(props.totalCost+props.price)
      setQuantity(quantity+1)
      try {       
        const response = await axios.post('http://localhost:8081/cart/update-product-amount',body, {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`
          },});
          
        setData(response.data);
      } catch (error) {
        setError(error.message);
      }      
    }
  }
  async function decrease(){
    if(quantity>1){
      const body ={
        productId:props.id,
        amount:quantity-1
      }
      props.changeCost(props.totalCost-props.price)
      setQuantity(quantity-1)
      
      try {       
        const response = await axios.post('http://localhost:8081/cart/update-product-amount',body, {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`
          },});
        setData(response.data);
      } catch (error) {
        setError(error.message);
      }      

    }
  }
  async function remove(){

    try {       
      const response = await axios.post('http://localhost:8081/cart/remove-product',props.id, {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        },});
      setData(response.data);
    } catch (error) {
      setError(error.message);
    }      
    props.onRemove(props.id)
  }
    return ( 
        <div className='itemInCart'> 
          <div onClick={handleclick} key={props.id} className="tile" style={{ float: 'left', width:'70vw'}}> {/*go to product*/} 
            <img src={missingImage}/>
            <b>{(props.price*quantity).toFixed(2)}</b>
            <h2>{props.name}</h2>
            <p>{props.seller}</p>            
          </div>
          <div className='itemCount'>
            <b>{quantity}</b>
            <div className='quantity-buttons' style={{ float: 'left', marginTop:'0px'}}>
              <p><button onClick={increase}>+</button></p>
              <p><button onClick={decrease}>-</button></p>
              </div>
              <button className='remove' onClick={remove}>Usuń</button>
          </div>
          <div className='clearfix'></div>
        </div>         
    );
  }