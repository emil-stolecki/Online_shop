import React, { useState, useEffect } from 'react';
import missingImage from '../images/missing_image_tile.png';
import { useNavigate } from 'react-router-dom';

export default function ProductPreviewTile(props) {
  const [quantity,setQuantity]=useState(props.amount);
  const navigate = useNavigate();
 
  function handleclick() {
    
    const productName = encodeURIComponent(props.name);
    const productId = encodeURIComponent(props.id);

    const url = `/product?name=${productName}&id=${productId}`;
    console.log(url)
    navigate(url); 
  };
  function increase(){
    setQuantity(quantity+1)
  }
  function decrease(){
    if(quantity>1){
      setQuantity(quantity-1)
    }
  }
  function remove(){
    props.onRemove(props.id)
  }
    return ( 
        <div className='itemInCart'> 
          <div onClick={handleclick} key={props.id} className="tile" style={{ float: 'left', width:'70vw'}}> {/*go to product*/} 
            <img src={missingImage}/>
            <b>{props.price*quantity}</b>
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