import React, {useEffect, useState } from 'react';
import axios from 'axios';
export default function Review(props) {
    

    async function onDelete(){
        try {
            const response = await axios.post('http://localhost:8081/product/review/delete',props.id,{headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${localStorage.getItem('token')}`
            }});        
            props.onRemove()
          } catch (error) {

          }
    }
    return(
        <div className="review">
           <p style={{ float: 'left'}}><b>{props.user}</b></p>
           {props.userId==props.loggedUserId&&
           <p style={{ float: 'right'}}><button style={{width:'20px', padding:'1px'}} onClick={()=>(onDelete())}>X</button></p>
           }
           <p style={{ float: 'right'}}><b>ocena: {props.rating}/10</b></p>
           <div className='clearfix'></div>
           <p>{props.content}</p>
        </div>
    )
}