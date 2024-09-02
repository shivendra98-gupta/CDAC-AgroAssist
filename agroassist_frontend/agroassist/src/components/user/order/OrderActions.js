// src/components/user/OrderActions.js
import React, { useContext } from 'react';
import { OrderContext } from './OrderContext';

const OrderActions = () => {
  const { createOrder } = useContext(OrderContext);

  return (
    <div>
      <h2>Create a New Order</h2>
      <button onClick={createOrder}>Place Order</button>
    </div>
  );
};

export default OrderActions;
