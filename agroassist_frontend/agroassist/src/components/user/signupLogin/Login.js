// src/components/user/signupLogin/Login.js
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './../../../styles/userStyle/signuplogin.css';

const Login = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });
  const [error, setError] = useState('');
  const navigate = useNavigate(); // Updated hook

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post('http://localhost:8080/users/login', formData)
      .then(response => {
        const { id, name, role, address } = response.data;
        // Store user data in localStorage or context
        localStorage.setItem('user', JSON.stringify({ id, name, role, address }));

        // Navigate based on role
        if (role === 'admin') {
          navigate('/admin-dash'); // Navigate to Admin Dashboard
        } else {
          navigate('/user_dashboard'); // Navigate to User Dashboard
        }
      })
      .catch(error => {
        setError(error.response?.data?.message || 'Something went wrong');
      });
  };

  return (
    <div className="auth-container">
      <h2>Login</h2>
      {error && <p className="error">{error}</p>}
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;
