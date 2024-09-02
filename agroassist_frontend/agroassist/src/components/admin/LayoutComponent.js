import React from 'react';
import { Outlet } from 'react-router-dom';
import HomeComponent from './HomeComponent'; // Adjust the import path as needed
import './../../styles/adminStyles/LayoutComponent.css'; // Optional, for additional layout styling

const LayoutComponent = () => {
    return (
        <div className="layout-container">
            <HomeComponent />
            <main className="main-content">
                
                <Outlet /> {/* This renders the matched child route */}
            </main>
        </div>
    );
};

export default LayoutComponent;
