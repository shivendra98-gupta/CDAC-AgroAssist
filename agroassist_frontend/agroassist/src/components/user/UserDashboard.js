// src/components/user/UserDashboard.js
import React from 'react';
import { Link } from 'react-router-dom';
import './../../styles/userStyle/UserDashboard.css'; // Add your CSS file for styling

const UserDashboard = () => {
  return (
    <div className="dashboard">
      <header className="navbar">
        <h1>Agro-Assist</h1>
        <nav>
          <Link to="/signup">Signup</Link>
          <Link to="/login">Login</Link>
        </nav>
      </header>

      <main className="main-content">
        <img src="https://i.pinimg.com/originals/7d/fe/04/7dfe04fc647c1909175292413660f541.png" alt="Centered" width={760} className="center-image" />
      </main>

      <footer className="footer">
        <p>&copy; 2024 Agro-Assist. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default UserDashboard;
