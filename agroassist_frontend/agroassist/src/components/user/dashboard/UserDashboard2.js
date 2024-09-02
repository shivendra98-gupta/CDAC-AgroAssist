// src/components/user/UserDashboard.js
import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './../../../styles/userStyle/dashboard.css';

const UserDashboard2 = () => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Retrieve user info from localStorage
    const userInfo = JSON.parse(localStorage.getItem('user'));
    if (userInfo) {
      setUser(userInfo);
    } else {
      navigate('/login'); // Redirect to login if no user info
    }
  }, [navigate]);

  const handleLogout = () => {
    localStorage.removeItem('user'); // Clear user data from localStorage
    navigate('/login'); // Redirect to login page
  };

  return (
    <div className="dashboard-container">
      <nav className="navbar">
        <Link to="/homepage">Home</Link>
        <Link to="/product-list">Buy Products</Link>
        <Link to="/create-order">Orders</Link>
        <Link to="/cart">Cart</Link>
        <Link to="/purchase">Purchase</Link>
        <button onClick={handleLogout}>Logout</button>
      </nav>
      <div className="user-info">
        {user ? (
          <>
            <h1>Welcome, {user.name}</h1>
            <p>Address: {user.address}</p>
            <p>Role: {user.role}</p>
          </>
        ) : (
          <p>Loading...</p>
        )}
      </div>
    </div>
  );
};

export default UserDashboard2;
