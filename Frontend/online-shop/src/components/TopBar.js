import { useNavigate } from 'react-router-dom';

export default function TopBar() {
  const navigate = useNavigate();
  const handleRegisterClick = () => {
    navigate('/register'); 
  };
  
  const handleLoginClick = () => {
    navigate('/login'); 
  };
    return (    
          <div className="topbar">
            <h1>Online-Shop</h1>
            <div className="register-login-profile">
              <button onClick={handleRegisterClick} >register</button>
              <button onClick={handleLoginClick} >login</button>
            </div>           
          </div>       
    );
  }