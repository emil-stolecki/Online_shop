
import missingImage from '../images/missing_image_tile.png';
import { useNavigate } from 'react-router-dom';
export default function ProductPreviewTile(props) {
  const navigate = useNavigate();
 
  function handleclick() {

    const productName = encodeURIComponent(props.name);
    const productId = encodeURIComponent(props.id);

    const url = `/product?name=${productName}&id=${productId}`;
    console.log(url)
    navigate(url); 
  };

    return (    
          <div onClick={handleclick} key={props.id} className="tile">  
            <img src={missingImage}/>
            <b>{props.price}</b>
            <h2>{props.name}</h2>
            <p>{props.seller}</p>
            
          </div>       
    );
  }