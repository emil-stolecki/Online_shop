import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ProductPreviewTile from './ProductPreviewTile';


export default function HomePageContent(props) {
    const [data, setData] = useState([]);
    const [error, setError] = useState(null);
    useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await axios.get('http://localhost:8081/home');
            setData(response.data);
            
            console.log(response.data)
            
          } catch (error) {
            setError(error.message);
          }
        };
        fetchData();
      }, []); 

    useEffect(() => {       
          const handlePostRequest = async () => {
           
            try {
              const response = await axios.post('http://localhost:8081/filter', props.filter,{withCredentials: true,});
              setData(response.data);
              console.log(response.data)
             
                const response2 = await axios.post('http://localhost:8081/filter/count', props.filter,{withCredentials: true,});
                props.handleCount(response2.data)
                console.log("count")
                console.log(response2.data)
              
             
            } catch (error) {
              setError(error.message);
            }
          };
    
          if (props.filter) {
            handlePostRequest();
          }else {
            setData(null);
          }
      }, [props.filter]);

      useEffect(()=>{
        props.onConfirm()
      
      },[props.category])
     

    return (
          <div className="content">
           {error && <p>Error: {error}</p>}
           {data && data.length > 0 ? ( 
        <ul>
          {data.map(product => ( 
            <li key={product.id}><ProductPreviewTile id={product.id} name={product.name} price={product.price.toFixed(2)} seller={product.seller}/></li>         
          ))}
        </ul>
      ) : (
        <p>No data available</p>
      )}
          </div>       
    );
  }