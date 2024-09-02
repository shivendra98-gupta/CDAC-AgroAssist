// src/components/user/Cart.js
import React, { useContext } from 'react';
import { CartContext } from './CartContext';

const Cart = () => {
  const { cart, removeFromCart } = useContext(CartContext);

  return (
    <div>
      <h2>Your Cart</h2>
      {cart.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <div className="cart-list">
          {cart.map(item => (
            <div key={item.id} className="cart-item">
              <h3>{item.product.productName}</h3>
              <p>Quantity: {item.productQuantity}</p>
              <p>Price: ${item.product.productPrice - item.product.productDiscount}</p>
              <button onClick={() => removeFromCart(item.id)}>Remove</button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Cart;
