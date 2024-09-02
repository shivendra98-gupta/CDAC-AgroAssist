// src/components/user/OrderContext.js
import React, { createContext, useState, useEffect } from 'react';
import axios from 'axios';

export const OrderContext = createContext();

export const OrderProvider = ({ children }) => {
  const [orders, setOrders] = useState([]);
  const [orderItems, setOrderItems] = useState([]);
  const [productsInOrder, setProductsInOrder] = useState([]);
  const userId = 1; // Replace with dynamic userId

  useEffect(() => {
    fetchOrdersByUserId(userId);
  }, []);

  const fetchOrdersByUserId = async (userId) => {
    try {
      const response = await axios.get(`http://localhost:8080/orders/user/${userId}`);
      setOrders(response.data);
    } catch (error) {
      console.error('Error fetching orders:', error);
    }
  };

  const fetchOrderItemsByOrderId = async (orderId) => {
    try {
      const response = await axios.get(`http://localhost:8080/orders/all_orderitems/${orderId}`);
      setOrderItems(response.data);
    } catch (error) {
      console.error('Error fetching order items:', error);
    }
  };

  const fetchProductsByOrderId = async (orderId) => {
    try {
      const response = await axios.get(`http://localhost:8080/orders/products/${orderId}`);
      setProductsInOrder(response.data);
    } catch (error) {
      console.error('Error fetching products by order:', error);
    }
  };

  const createOrder = async () => {
    try {
      const response = await axios.post(`http://localhost:8080/orders/${userId}`);
      setOrders([...orders, response.data]);
    } catch (error) {
      console.error('Error creating order:', error);
    }
  };

  const deleteOrder = async (orderId) => {
    try {
      await axios.delete(`http://localhost:8080/orders/${orderId}`);
      setOrders(orders.filter(order => order.id !== orderId));
    } catch (error) {
      console.error('Error deleting order:', error);
    }
  };

  return (
    <OrderContext.Provider value={{
      orders, orderItems, productsInOrder,
      fetchOrdersByUserId, fetchOrderItemsByOrderId,
      fetchProductsByOrderId, createOrder, deleteOrder
    }}>
      {children}
    </OrderContext.Provider>
  );
};
