import React, {useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import Topbar from '../components/TopBar';
import Review from '../components/Review';
import Star from '../components/Star';
import axios from 'axios';
import missingImage from '../images/missing_image_tile.png';

export default function Product(props) {

  const [data, setData] = useState([]);
  const [error, setError] = useState([]);
  const [newReview,setNewReview]=useState(null)
  const [OldReviewId,setOldReviewId]=useState(null)
  const [quantity,setQuantity]=useState(1);
  const [reviewError,setReviewError]=useState(null)
  const [stars,setStars]=useState(0)

  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const id = searchParams.get('id');

  const normal_style={
    color: '#00236a'
  }
  const too_much_words_style={
    color: '#a85032'
  }
  
  const [textareaStyle,setStyle]=useState(normal_style)
  
  useEffect(() => {
    document.body.style.cursor = 'wait'  
    const fetchData = async () => {
      try {
        const response = await axios.post('http://localhost:8081/product',id,{headers: {
          'Content-Type': 'application/json',
        }});
        console.log(response.data.reviews)
        setData(response.data);
        
        const d = response.data.reviews.forEach(obj => {         
          if(obj.userId == localStorage.getItem('id')){
            setNewReview(obj.content)
            setStars(obj.rating)   
            setOldReviewId(obj.id)        
          }
      })

      } catch (error) {
        setError(error.message);
      }
    };
    fetchData();
    document.body.style.cursor = 'default'
  }, []); 

  let result = "";
  let features=[];
  let description=""
  let left_features=[]
  let right_features=[]
  if(data!=null){
    let pattern = /\{([^}]*)\}/;
    let match = pattern.exec(data.description);
    if (match) {
      result = match[1];
      features=result.split(',')
      description = data.description.substring(match.index + match[0].length);
      features.forEach((feature)=>{
        let f=feature.split(':')
        left_features.push(f[0])
        right_features.push(f[1])
      })
      }
  }
  
  function increase(){
    if(quantity<data.amountInStock){
      setQuantity(quantity+1)
    }
    
  }
  function decrease(){
    if(quantity>1){
      setQuantity(quantity-1)
    }
  } 
 
 function get_reviews(){
    if(data!=null){     
      if(data.reviews!=undefined){
        if(data.reviews.length>0){
          let rating=data.reviews.reduce(
            (accumulator, currentValue) => accumulator + currentValue.rating,0);
          return(<div>
            <p>oceniane na: {(rating/data.reviews.length).toFixed(2)} </p>
            <ul>
            {data.reviews.map((review)=>(
              <li><Review user={review.userName} content={review.content} rating={review.rating} 
               id={review.id} onRemove={remove_review}/></li>
            ))}
            </ul>
          </div>)
        }
        else{
          return(<p>Nie ma jeszcze recenzji</p>)
        }
      }
      
    }
  }

  function can_add_review(){
    if(localStorage.getItem('token')==""){
      return(
      <p>-----zaloguj się żeby dodać recenzję-----</p>
      )
    }
    else{
      return(
        <div>
          <div >
           <Star id={1} setStars={setStars} rating={stars}/>
           <Star id={2} setStars={setStars} rating={stars}/>
           <Star id={3} setStars={setStars} rating={stars}/>
           <Star id={4} setStars={setStars} rating={stars}/>
           <Star id={5} setStars={setStars} rating={stars}/>
           <Star id={6} setStars={setStars} rating={stars}/>
           <Star id={7} setStars={setStars} rating={stars}/>
           <Star id={8} setStars={setStars} rating={stars}/>
           <Star id={9} setStars={setStars} rating={stars}/>
           <Star id={10} setStars={setStars} rating={stars}/>
          </div>
          <div className='clearfix'></div>
          <textarea placeholder='max. 500 znaków' name="new_review" value={newReview} onChange={handleChange} style={textareaStyle}></textarea>
          {reviewError&&<p>{reviewError}</p>}
          {OldReviewId&&<button onClick={handleEditReview}>Aktualizuj</button>}
          {!OldReviewId&&<button onClick={handleSubmitReview}>Dodaj</button>}
        </div>
      )
    }
  }
  const handleChange = (e) => {
    setNewReview(e.target.value);
    if(e.target.value.length>500){
      setStyle(too_much_words_style)
    }
    else{
      setStyle(normal_style)
    }
  };
  async function handleEditReview(){
    
    const body={
      id:OldReviewId,
      userId:0,
      userName:'',
      productId:id,
      productName:'',
      rating:stars,
      content:newReview
    }
    document.body.style.cursor = 'wait'  
    try {
      const response = await axios.post('http://localhost:8081/product/review/edit',body,{headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }});
      
      const newReviews = data.reviews.map(obj => {
        if (obj.id === OldReviewId) {
          obj.content = newReview;
          obj.rating = stars;
        }
        return obj;
      });
      const newData=data
      newData.reviews=newReviews
      setData(newData)

      
    } catch (error) {
      setError(error.message);
    } 
    document.body.style.cursor = 'default'
  }
  function remove_review(){
    const newReviews = data.reviews.filter(obj => !(obj.id === OldReviewId))  
    const newData=data
    newData.reviews=newReviews
    setData(newData)
    setOldReviewId(null)
  }
  async function handleSubmitReview() {
    if(newReview.length<=500 && newReview.length>0){
     const body={
        productId:id,
        rating:stars,
        content:newReview
      }
      document.body.style.cursor = 'wait'  
      try {
        const response = await axios.post('http://localhost:8081/product/review-add',body,{headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }});

        const currentData=data
        currentData.reviews.push(response.data)
        setOldReviewId(response.data.id)

        setData(currentData)
      } catch (error) {
        setError(error.message);
      } 
    document.body.style.cursor = 'default'

    }    
    else if (newReview.length>500){
      setReviewError("Przekroczono dozwoloną liczbę znaków")
    }
    else{
      setReviewError("Pole nie zawiera tekstu")
    }
  };
  async function add_to_cart(){
    document.body.style.cursor = 'wait'  
      const body ={
        productId:id,
        amount:quantity
      }
      try {        
        const response = await axios.post('http://localhost:8081/cart/add-product',body,{
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`
          },});
      } catch (error) {
        setError(error.message);
      }
       
    document.body.style.cursor = 'default'
  }
  return (    
    <div>
       <Topbar parent='product'/>
        <div className='clearfix'></div>
        <div className='product'>
          
          <div className='product_col_left'>
            <div className='product_name'>
            <h1>{data && data.name}</h1>
            <h2>sprzedawca: {data && data.seller}</h2>
            </div>
            <div className='product_image'>
               <img src={missingImage}/>
            </div>
            <div className='product_description'>             
              <div className='product_desc_left'>
                <ul>
                    {left_features.map(feature=>(
                  <li>{feature}</li>  
                  )) }                
                </ul>
              </div>
              <div className='product_desc_right'>
                <ul>            
                {right_features.map(feature=>(
                  <li>{feature}</li>  
                  )) }         
                </ul>
              </div>
              
              <div className='clearfix'></div>
            
              {data && description}
            </div>
          </div>
          <div className='product_col_right'>
            
            <div className='product_price'>
              <h1>{data.price && data.price.toFixed(2)} PLN</h1>
              <p>W magazynie: {data && data.amountInStock}</p>
            </div>
            <div className='add_to_cart'>              
              
              <div className='quantity-buttons'>
              <p><button onClick={increase}>+</button></p>
              <p><button onClick={decrease}>-</button></p>
              </div>
              <div style={{ float: 'right', fontSize:'40px'}}>
                <p>{quantity}</p>
                </div>
              <div className='clearfix'></div>
              <button className='add_to_cart_button' onClick={add_to_cart}>Do Koszyka</button>
            </div>
            <div className='reviews'>
            
                  <h2>Recenzje:</h2>
                  {can_add_review()}
                  {get_reviews()}
            </div>
          </div>
          <div className='clearfix'></div>
          
        </div>
    </div>       
);
}