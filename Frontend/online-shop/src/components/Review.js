export default function Review(props) {

    return(
        <div className="review">
           <p style={{ float: 'left'}}><b>{props.user}</b></p>
           <p style={{ float: 'right'}}><b>ocena: {props.rating}/10</b></p>
           <div className='clearfix'></div>
           <p>{props.content}</p>
        </div>
    )
}