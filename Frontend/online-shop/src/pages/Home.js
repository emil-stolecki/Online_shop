import HomePageContent from '../components/HomePageContent';
import Filter from '../components/Filter';
import LeftBar from '../components/LeftBar';
import Topbar from '../components/TopBar';
import React, { useState } from 'react';
export default function Home() {

  const [filterValue, setFilterParams] = useState({
    min:null,
    max:null, 
    category:null,
    name:null,
  });
  const [filterCount, setFilterCount] = useState(null);
  const [postFilterData, setPostFilterData] = useState({
    min:null,
    max:null,
    category:null,
    name:null,
    offset:0,
    limit:10
  });
  const handleFilterChange = (param, value) => {
    setFilterParams((prevParams) => ({
      ...prevParams,
      [param]: value,
    }));
  };
  const handleCountChange = (value)=>{
    setFilterCount(value)
  }
  const handleConfirm = () => {
    setPostFilterData(() => ({
      ['minPrice']: filterValue.min,
      ['maxPrice']: filterValue.max,
      ['category']: filterValue.category,
      ['name']: filterValue.name,
      ['offset']: filterValue.offset
    }))
  };
  const clearFilter = () => {
    setPostFilterData(() => ({
      ['minPrice']: null,
      ['maxPrice']: null,
      ['category']: null,
      ['name']: null,
    }))
  };

    return (    
          <div>
            <div className='fixed'>
            <Topbar/>
            <div className='clearfix'></div>
            <Filter onFilterChange={ handleFilterChange } onConfirm={handleConfirm} onClear={clearFilter} count={filterCount}/>
            <div className='clearfix'></div>
            <LeftBar onFilterChange={ handleFilterChange }/>
            </div>
            <HomePageContent filter={postFilterData} handleCount={handleCountChange}/>
            <div className='clearfix'></div>
          </div>
        
    );
  }