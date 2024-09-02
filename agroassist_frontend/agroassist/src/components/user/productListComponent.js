import React, { useState, useEffect, useContext } from 'react';
import axios from 'axios';
import { CartContext } from './CartContext';
import { Link } from 'react-router-dom';
import './../../styles/userStyle/productList.css'; // Import the CSS file

const ProductListComponent = () => {
  const [products, setProducts] = useState([]);
  const { addToCart, cartItems } = useContext(CartContext);
  const [cartCount, setCartCount] = useState(0);
  const [quantities, setQuantities] = useState({});
  const [userId, setUserId] = useState(null);

  useEffect(() => {
    // Fetch userId from localStorage or context
    const storedUserId = JSON.parse(localStorage.getItem('user')).id;
    setUserId(storedUserId);
  }, []);

  useEffect(() => {
    axios.get('http://localhost:8080/products')
      .then(response => {
        setProducts(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the products!', error);
      });
  }, []);

  useEffect(() => {
    setCartCount(cartItems ? cartItems.length : 0);
  }, [cartItems]);

  const handleQuantityChange = (e, productId) => {
    setQuantities({
      ...quantities,
      [productId]: e.target.value
    });
  };

  const handleAddToCart = (product) => {
    const quantity = quantities[product.id] ? parseInt(quantities[product.id], 10) : 1;
    addToCart({ ...product, quantity });

    // Check if userId is available
    if (userId !== null) {
      // Send data to backend
      axios.post(`http://localhost:8080/carts/${userId}`, {
        productId: product.id,
        productQuantity: quantity
      })
      .then(response => {
        console.log('Item added to cart:', response.data);
      })
      .catch(error => {
        console.error('Error adding item to cart:', error);
      });
    } else {
      console.error('User ID is not available');
    }
  };

  return (
    <div>
      <h2>Product List</h2>
      <div className="product-list">
        {products.map(product => (
          <div key={product.id} className="product-item">
            <h3>{product.productName}</h3>
            <p>Price: Rs {product.productPrice.toFixed(2)}</p>
            <label>
              Quantity:
              <input
                type="number"
                min="1"
                value={quantities[product.id] || 1}
                onChange={(e) => handleQuantityChange(e, product.id)}
              />
            </label>
            <button onClick={() => handleAddToCart(product)}>Add to Cart</button>
          </div>
        ))}
      </div>
      <div className="cart-summary">
        <p>Items in Cart: {cartCount}</p>
        <Link to="/checkout">
          <button>Purchase</button>
        </Link>
      </div>
    </div>
  );
};

export default ProductListComponent;
