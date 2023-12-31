import './App.css';
import {
  BrowserRouter as Router,
  Routes,
  Route,
} from "react-router-dom";
import Home from './pages/Home.js';
import Register from './pages/Register.js';
import Login from './pages/Login.js';
import Product from './pages/Product.js';
import Profile from './pages/Profile.js';
import Cart from './pages/Cart.js';
import Checkout from './pages/Checkout.js';
import Empty from './pages/Empty.js';

function App() {

  return (    
    <Router>
      <Routes>
        <Route path="/" element={<Empty/>}></Route>
        <Route path="/home" element={<Home />} />
        <Route path="/register" element={<Register/>} />
        <Route path="/login" element={<Login />} />
        <Route path="/product" element={<Product />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/checkout" element={<Checkout/>} />
      </Routes>
    </Router>  
  
);
}

export default App;
