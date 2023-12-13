import React, {useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import Topbar from '../components/TopBar';
import Review from '../components/Review';
import axios from 'axios';
import missingImage from '../images/missing_image_tile.png';

export default function Product(props) {
  const [data, setData] = useState([]);
  const [error, setError] = useState([]);

  const [quantity,setQuantity]=useState(1);

  const location = useLocation();
  
  const searchParams = new URLSearchParams(location.search);

  const id = searchParams.get('id');
  
  useEffect(() => {
    const fetchData = async () => {
      try {
        const userId=(props.user==null)?0:props.user.id;
        const body = { userId:  userId, productId: id };
        const response = await axios.post('http://localhost:8081/product',body);
        setData(response.data);
        console.log(response.data)
      } catch (error) {
        setError(error.message);
      }
    };
    fetchData();
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
    setQuantity(quantity+1)
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
            <p>oceniane na: {(rating/data.reviews.length).toFixed(2)}
            </p>
            <ul>
            {data.reviews.map((review)=>(
              <li><Review user={review.userName} content={review.content} rating={review.rating}/></li>
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

  return (    
    <div>
       <Topbar isLogged={props.islogged} setLogged={props.setLogged}/>
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
              <button className='add_to_cart_button'>Do Koszyka</button>
            </div>
            <div className='reviews'>
            
                  <h2>Recenzje:</h2>
                  <p>-----zaloguj się żeby dodać recenzję-----</p>
                  {get_reviews()}
            </div>
          </div>
          <div className='clearfix'></div>
          
        </div>
    </div>       
);
}