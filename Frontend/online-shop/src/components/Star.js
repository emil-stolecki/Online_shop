import React, {useState,useEffect } from 'react';
import star from '../images/star.png';
export default function Star(props) {

    const star_normal_style={
        filter: 'brightness(1)'
      }
      const star_hover_style={
        filter:'brightness(2)'
      }
      const star_active_style={
        filter:'brightness(1.5)'
      }
      const [starStyle,setStarStyle]=useState(star_normal_style)

      function star_handle_hover(){
        if(starStyle.filter=='brightness(1)'){
            setStarStyle(star_hover_style)     
        }
             
    
    } 
      function star_handle_leave(){
        if(starStyle.filter=='brightness(2)'){
            setStarStyle(star_normal_style) 
        }
    
    }
    
    function star_handleClick(){      
        props.setStars(props.id)       
    }

    useEffect(()=>{
        if(props.rating>=props.id){
            setStarStyle(star_active_style) 
        } 
        else{
            setStarStyle(star_normal_style)
        }  
    },[props.rating])

    return(
        <div style={{float:'left'}}>
           <img className="star"src={star} style={starStyle} onMouseOver={ star_handle_hover} onMouseLeave={ star_handle_leave}
            onClick={ star_handleClick}/>
        </div>
    )
}