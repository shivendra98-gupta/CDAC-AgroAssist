import React, { useContext, useEffect, useState } from 'react';
import { CartContext } from './CartContext';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // Updated import
import './../user/../../styles/userStyle/checkout.css'; // Import the CSS file

const Checkout = () => {
  const { cartItems, clearCart } = useContext(CartContext);
  const [total, setTotal] = useState(0);
  const [userId] = useState(1); // Replace with actual userId from context or other source
  const navigate = useNavigate(); // Updated hook

  useEffect(() => {
    // Calculate total amount
    const calculateTotal = () => {
      return cartItems.reduce((sum, item) => 
        sum + (item.productPrice - item.productDiscount) * item.productQuantity, 0);
    };

    setTotal(calculateTotal());
  }, [cartItems]);

  const handlePurchase = async () => {
    try {
      // Make API call to create order by userId
      const response = await axios.post(`http://localhost:8080/orders/${userId}`, null, {
        headers: {
          'Content-Type': 'application/json'
        }
      });

      // Check if response is successful
      if (response.status === 200) {
        // Clear cart after successful purchase
        clearCart();
        alert('Order placed successfully!');
        navigate('/homepage'); // Redirect to homepage
      } else {
        throw new Error('Unexpected response status');
      }
    } catch (error) {
      alert('An error occurred while placing the order. Please try again.');
      console.error('Error placing order:', error.response ? error.response.data : error.message);
    }
  };

  return (
    <div className="checkout-container">
      <h2>Checkout</h2>
      <div className="checkout-summary">
        <h3>Cart Items:</h3>
        <ul>
          {cartItems.map(item => (
            <li key={item.id}>
              {item.productName} - {item.productQuantity} x ${item.productPrice.toFixed(2)}
            </li>
          ))}
        </ul>
        <h3 className="total-amount">Total: ${total.toFixed(2)}</h3>
        <button onClick={handlePurchase}>Place Order</button>
      </div>
    </div>
  );
};

export default Checkout;
