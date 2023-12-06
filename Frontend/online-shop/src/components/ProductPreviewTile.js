
import missingImage from '../images/missing_image_tile.png';
export default function ProductPreviewTile(props) {
    return (    
          <div className="tile">  
            <img src={missingImage}/>
            <b>{props.price}</b>
            <h2>{props.name}</h2>
            <p>{props.seller}</p>
            
          </div>       
    );
  }