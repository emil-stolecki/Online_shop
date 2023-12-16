import HomePageContent from '../components/HomePageContent';
import Filter from '../components/Filter';
import LeftBar from '../components/LeftBar';
import Topbar from '../components/TopBar';
import React, { useState } from 'react';

export default function Home(props) {

  const [filterValue, setFilterParams] = useState({
    min:null,
    max:null, 
    name:null,
    offset:0
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
  const [category, setCategory] = useState(null);
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
      ['category']: category,
      ['name']: filterValue.name,
      ['offset']: filterValue.offset
    }))
    console.log("confiremd")
    console.log(postFilterData)
  };
  const clearFilter = () => {
    console.log("clearing filter")
    setPostFilterData(() => ({
      ['minPrice']: null,
      ['maxPrice']: null,
      ['category']: null,
      ['name']: null,
      ['offset']: 0,
      ['limit']: 10
    }))

    setFilterParams(()=>({
      ['minPrice']: null,
      ['maxPrice']: null,
      ['category']: null,
      ['name']: null,
      ['offset']: 0
    })
    )
  };
  
    return (    
          <div>
            <Topbar parent='home'/>
            <div className='clearfix'></div>
            <Filter onFilterChange={ handleFilterChange } onConfirm={handleConfirm} onClear={clearFilter} count={filterCount} changepage={setPostFilterData} />
            <div className='clearfix'></div>
            
            <LeftBar onFilterChange={ handleFilterChange } onCategoryChange={setCategory} />
            
            <HomePageContent filter={postFilterData} handleCount={handleCountChange} category={category} onConfirm={handleConfirm}/>
            <div className='clearfix'></div>
          </div>
        
    );
  }