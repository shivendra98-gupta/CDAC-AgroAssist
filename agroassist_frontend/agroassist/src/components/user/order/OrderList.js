// src/components/user/OrderList.js
import React, { useContext, useEffect } from 'react';
import { OrderContext } from './OrderContext';

const OrderList = () => {
  const { orders, fetchOrdersByUserId, deleteOrder } = useContext(OrderContext);
  const userId = 1; // Replace with dynamic userId

  useEffect(() => {
    fetchOrdersByUserId(userId);
  }, [fetchOrdersByUserId, userId]);

  return (
    <div>
      <h2>Your Orders</h2>
      {orders.length === 0 ? (
        <p>No orders found.</p>
      ) : (
        <div className="order-list">
          {orders.map(order => (
            <div key={order.id} className="order-item">
              <h3>Order #{order.id}</h3>
              <p>Delivery Status: {order.delivery_status}</p>
              <p>Total Amount: ${order.total_amount}</p>
              <button onClick={() => deleteOrder(order.id)}>Delete Order</button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default OrderList;
