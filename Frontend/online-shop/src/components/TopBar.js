
import { useNavigate } from 'react-router-dom';

export default function TopBar(props) {

  const userName=localStorage.getItem('userName');

  const navigate = useNavigate();
  const handleRegisterClick = () => {
    navigate('/register'); 
  };
  
  const handleLoginClick = () => {
    navigate('/login'); 
  };

  const handleLogoutClick = () => {   
    localStorage.setItem('token',"")
    navigate("/home")

  };
  const handleProfileClick = () => {
    navigate('/profile'); 
  };
  const handleCartClick = () => {
    navigate('/cart'); 
  };

  function get_view_for_logged_or_not(){
    if(localStorage.getItem('token').length>0){
      return(
        <div className="register-login-profile">
              {(props.parent!=='cart')&&<button onClick={handleCartClick} >cart</button>}
              {(props.parent!=='profile')&&<button onClick={handleProfileClick} >profile</button>}
              <button onClick={handleLogoutClick} >logout</button>
            </div>   
      )
    }
    else{

        return(
          <div className="register-login-profile">
              
              <button onClick={handleRegisterClick} >register</button>
              <button onClick={handleLoginClick} >login</button>
            </div>   
        )
    }
  }
    return (             
          <div className="topbar">
            <div onClick={()=>{navigate('/home')}} className='invisible-button'></div>
            <h1>Online-Shop</h1>
            <div className="register-login-profile">
              {get_view_for_logged_or_not()}
            </div>           
          </div>       
    );
  }