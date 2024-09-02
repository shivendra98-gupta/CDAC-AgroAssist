// src/components/user/OrderDetails.js
import React, { useContext, useEffect } from 'react';
import { OrderContext } from './OrderContext';

const OrderDetails = ({ orderId }) => {
  const { orderItems, productsInOrder, fetchOrderItemsByOrderId, fetchProductsByOrderId } = useContext(OrderContext);

  useEffect(() => {
    fetchOrderItemsByOrderId(orderId);
    fetchProductsByOrderId(orderId);
  }, [orderId, fetchOrderItemsByOrderId, fetchProductsByOrderId]);

  return (
    <div>
      <h2>Order Details for Order #{orderId}</h2>
      <div className="order-items">
        <h3>Items in this Order</h3>
        {orderItems.length === 0 ? (
          <p>No items found in this order.</p>
        ) : (
          <ul>
            {orderItems.map(item => (
              <li key={item.id}>
                Product ID: {item.product_id}, Quantity: {item.quantity}
              </li>
            ))}
          </ul>
        )}
      </div>
      <div className="products-in-order">
        <h3>Products in this Order</h3>
        {productsInOrder.length === 0 ? (
          <p>No products found in this order.</p>
        ) : (
          <ul>
            {productsInOrder.map(product => (
              <li key={product.id}>
                {product.productName} - ${product.productPrice}
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default OrderDetails;
