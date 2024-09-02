import React, { useState } from 'react';
import axios from 'axios';
import './../../styles/adminStyles/AdminProductForm.css';
import { Link } from 'react-router-dom';

const AdminProductForm = () => {
    const [product, setProduct] = useState({
        productName: '',
        productDescription: '',
        quantity: 0,
        productPrice: 0.0,
        productDiscount: 0.0,
        productRating: 0,
        productImage: null, // Changed to null for file upload
        categoryName: ''    // Added categoryName
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setProduct({ ...product, [name]: value });
    };

    const handleFileChange = (e) => {
        setProduct({ ...product, productImage: e.target.files[0] });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        Object.keys(product).forEach(key => {
            formData.append(key, product[key]);
        });

        try {
            const response = await axios.post('http://localhost:8080/products', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            if (response.status === 200) {
                alert('Product added successfully!');
                setProduct({
                    productName: '',
                    productDescription: '',
                    quantity: 0,
                    productPrice: 0.0,
                    productDiscount: 0.0,
                    productRating: 0,
                    productImage: null,
                    categoryName: ''
                });
            } else {
                alert('Failed to add product.');
            }
        } catch (error) {
            console.error('Error adding product:', error);
            alert('An error occurred while adding the product.');
        }
    };

    return (
        <div className="home-container">
        <header className="navbar">
            <img src="data:image/jpeg;base64,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" alt="Logo" className="logo" />
            <h3>Agro-Assist</h3>
            <nav>
                <ul>
                    <li><Link to="/add-product">Add Product</Link></li>
                    <li><Link to="/all-products">All Products</Link></li>
                    <li><Link to="/all-users">All Users</Link></li>
                </ul>
            </nav>
            
        </header>
        <div>
             <div className="admin-form-container">
             <h2>Add New Product</h2>
             <form onSubmit={handleSubmit}>
                 <div>
                     <label>Product Name:</label>
                     <input type="text" name="productName" value={product.productName} onChange={handleChange} required />
                 </div>
                <div>
                     <label>Product Description:</label>
                     <textarea name="productDescription" value={product.productDescription} onChange={handleChange} required />
                 </div>
                 <div>
                     <label>Quantity:</label>
                     <input type="number" name="quantity" value={product.quantity} onChange={handleChange} required />
                 </div>
                 <div>
                     <label>Product Price:</label>
                     <input type="number" name="productPrice" value={product.productPrice} onChange={handleChange} required />
                 </div>
                 <div>
                     <label>Product Discount:</label>
                     <input type="number" name="productDiscount" value={product.productDiscount} onChange={handleChange} required />
                 </div>
                 <div>
                     <label>Product Rating:</label>
                     <input type="number" name="productRating" value={product.productRating} onChange={handleChange} required />
                 </div>
                 <div>
                     <label>Product Image:</label>
                     <input type="file" name="productImage" onChange={handleFileChange} required />
                 </div>
                 <div>
                     <label>Category Name:</label>
                     <input type="text" name="categoryName" value={product.categoryName} onChange={handleChange} required />
                 </div>
                <button type="submit">Add Product</button>
             </form>
         </div>
            </div>
        <footer className="footer">
            <p>&copy; 2024 Your Company. All rights reserved.</p>
        </footer>
    </div>

    );
};

export default AdminProductForm;
