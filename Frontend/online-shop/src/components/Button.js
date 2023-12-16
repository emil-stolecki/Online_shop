import React, {useState , useEffect} from 'react';

export default function Button(props) {  
    const [name, setName] = useState(props.name);

    const normal_color =  '#5bb294'
    const normal_hover_color =  '#dcdcdc'
    const clicked_color =  '#50a14d'
    const clicked_hover_color =  '#61d4c3'


    const normal_style ={
        width:'110px',
        backgroundColor: normal_color
      }
      const normal_hover ={
        width:'110px',
        backgroundColor: normal_hover_color
      }
      const clicked_style ={
        width:'110px',
        backgroundColor: clicked_color,
      }
      const clicked_hover ={
        width:'110px',
        backgroundColor: clicked_hover_color,
      }

      const [style, setStyle] = useState(normal_style);

      function handle_hover(){
        if(style.backgroundColor==normal_color){
          setStyle(normal_hover)          
        }
        else {
            setStyle(clicked_hover)             
        }  
    } 
      function handle_leave(){
        if(style.backgroundColor==normal_hover_color){
          setStyle(normal_style) 
          
        }
        else {
            setStyle(clicked_style)    
        }
    }

    function handleClick(){
        props.function()
        
        if(style.backgroundColor==normal_hover_color){
            setStyle(clicked_hover)
            let new_state = Array.from({ length: props.state.length }, () => false)
            new_state[props.id]=true
            props.adjustState(new_state)
            if(props.altName) {               
              setName(props.altName)

            }
        }      
        else  {
            setStyle(normal_hover)
            props.adjustState(Array.from({ length: props.state.length }, () => false))
            if(props.altName){            
              setName(props.name)

            }
        }
        
    
    }
    useEffect(()=>{
      if(props.state[props.id]==false){
        setStyle(normal_style)
        props.adjustState(Array.from({ length: props.state.length }, () => false))
        if(props.altName){            
          setName(props.name)
        }
      }
      
    },props.state)
    return(
    <div>
        <button  
        onMouseOver={handle_hover} onMouseLeave={handle_leave}
        onClick={handleClick} style={style}>
            {name}
        </button>
    </div>
    )
}