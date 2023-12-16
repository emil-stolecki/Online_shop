import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Button from '../components/Button';

export default function LeftBar(props) {
  const [data, setData] = useState([]);
  const [error, setError] = useState([]);
  const[buttons, setButtons]=useState(Array.from({ length: 10 }, () => false))

  const handlePickingCategory=(cat)=>{    
    props.onCategoryChange(cat)  
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
            <li>
              <Button id={category.id-1} name={category.name} altName={""} function={()=>handlePickingCategory(category.name)} adjustState={setButtons} state={buttons}/>
            </li>
            ))}
              </ul>
            </div>           
          </div>       
    );
  }