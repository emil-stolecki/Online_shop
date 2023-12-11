import React, { useState,useEffect } from 'react';
import axios from 'axios';

export default function Filter(props) {
    const [minInput, setMinInput] = useState();
    const [maxInput, setMaxInput] = useState();
    const [searchInput, setSearchInput] = useState('');
    const [page,setpage] = useState(0);
    const limit=25
    const handleMinPriceChange = (event) => {
        setMinInput(event.target.value);
        props.onFilterChange('min',event.target.value);
      };
    const handleMaxPriceChange = (event) => {
        setMaxInput(event.target.value);
        props.onFilterChange('max',event.target.value);
      };
    const handleSearchBarChange = (event) => {
        setSearchInput(event.target.value);
        props.onFilterChange('name',event.target.value);
      };

   const handleNext=()=>{
    if ((page+1)*limit<props.count){
      props.changepage((prev)=>({
        ...prev,
        ['offset']:page+1
      }))
      setpage(page+1)
    }
   }
   const handlePrev=()=>{
    if (page>0){
      props.changepage((prev)=>({
        ...prev,
        ['offset']:page-1
      }))
      setpage(page-1)   
    }
   }
   const handleConfirm=()=>{
      setpage(0);
      props.onConfirm()
   }

    return (    
          <div className="filter">
            <h3>Search</h3>            
            <div className="filter-elem">
                <p>Min Price</p>
                <input
                    type="number"
                    value={minInput}
                    onChange={handleMinPriceChange}
                />
            </div> 
            <div className="filter-elem">
            <p>Max Price</p>
            <input
                    type="number"
                    value={maxInput}
                    onChange={handleMaxPriceChange}
            />
            </div> 
            <div className="filter-elem">
            <p>Search</p>
            <input
                    type="text"
                    className='searchbar'
                    value={searchInput}
                    onChange={handleSearchBarChange}
            />
            </div>   
            <div className="filter-elem">
            <br/>
            <button onClick={handleConfirm}>Confirm</button>
            <button onClick={props.onClear}>Clear</button>

            </div>
            <div className='clearfix'></div> 
            <div className='pageswitcher'>
              Results: {props.count}
              <button onClick={handlePrev}>previous page</button>
              <button onClick={handleNext}>next page</button>

            </div>        
          </div>       
    );
  }