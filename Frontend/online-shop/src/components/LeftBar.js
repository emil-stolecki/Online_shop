import React, { useState, useEffect } from 'react';
import axios from 'axios';

export default function LeftBar(props) {
  const [data, setData] = useState([]);
  const [error, setError] = useState([]);

  const handlePickingCategory=(cat)=>{
    props.onFilterChange('category',cat);
    
  };

    useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await axios.get('http://localhost:8081/categories');
            setData(response.data);
          } catch (error) {
            setError(error.message);
          }
        };
        fetchData();
      }, []); 

      

    return (    
          <div className="leftbar">
            <h1>Categories</h1>
            <div className="categories">
              <ul>
              {data.map(category => (
            <li><button onClick={()=>handlePickingCategory(category.name)} key={category.id}> {category.name}</button></li>
            ))}
              </ul>
            </div>           
          </div>       
    );
  }