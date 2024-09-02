import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomeComponent from './components/admin/HomeComponent';
import AdminProductForm from './components/admin/AdminProductForm';
import AllProductsComponent from './components/admin/AllProductsComponent';
import ProductListComponent from './components/user/productListComponent';
import UpdateProductForm from './components/admin/UpdateProductForm';
import Cart from './components/user/Cart';
import { CartProvider } from './components/user/CartContext';
import { OrderProvider } from './components/user/order/OrderContext';
import OrderList from './components/user/order/OrderList';
import OrderDetails from './components/user/order/OrderDetails';
import OrderActions from './components/user/order/OrderActions';
import './App.css';
import Checkout from './components/user/Checkout';
import UserDashboard from './components/user/UserDashboard';
import Signup from './components/user/signupLogin/Signup';
import Login from './components/user/signupLogin/Login';
import UserDashboard2 from './components/user/dashboard/UserDashboard2';
import UserList from './components/user/UserList';


function App() {
  return (
    <CartProvider>
      <OrderProvider>
        <Router>
          <div className="App">
            <Routes>
              <Route path="/admin-dash" element={<HomeComponent />} />
              <Route path="/add-product" element={<AdminProductForm />} />
              <Route path="/all-products" element={<AllProductsComponent />} />
              <Route path="/product-list" element={<ProductListComponent />} />
              <Route path="/edit-product/:id" element={<UpdateProductForm />} />
              <Route path="/cart" element={<Cart />} />
              <Route path="/orders" element={<OrderList />} /> {/* Route for listing orders */}
              <Route path="/order/:orderId" element={<OrderDetails />} /> {/* Route for order details */}
              <Route path="/create-order" element={<OrderActions />} /> {/* Route for creating an order */}
              <Route path="/checkout" element={<Checkout />} /> {/* New route */}
              <Route path="/homepage" element={<UserDashboard />} /> {/* New route */}
              <Route path="/signup" element={<Signup />} /> {/* New route */}
              <Route path="/login" element={<Login />} /> {/* New route */}
              <Route path="/user_dashboard" element={<UserDashboard2 />} /> {/* New route */}
              <Route path="/all-users" element={<UserList />} /> {/* New route */}
            </Routes>
          </div>
        </Router>
      </OrderProvider>
    </CartProvider>
  );
}

export default App;
