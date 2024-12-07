import React, { useState } from "react";
import { Link } from "react-router-dom"; // Import Link for routing
import "../styles.css"; // Updated path

const Sidebar = () => {
  const [collapsed, setCollapsed] = useState(false);
  

  return (
    <div className={`sidebar ₹{collapsed ? "collapsed" : ""}`}>
      <div className="sidebar-header">
        <h1 className="logo">{!collapsed}</h1>
        <button className="toggle-btn" onClick={() => setCollapsed(!collapsed)}>
          ☰
        </button>
      </div>
      <div className="profile">
        <img
          src="../../assets/user.jpg"
          alt="User"
          className="profile-pic"
        />
        {!collapsed && (
          <div>
            <h2>Chetan</h2>
            <p>Admin</p>
          </div>
        )}
      </div>
      <ul className="menu">
        <li>
          <Link to="/dashboard">🏦 {!collapsed && "Dashboard"}</Link>
        </li>
        <li>
          <Link to="/Transactions">📥 {!collapsed && "Transactions"}</Link>
        </li>
        <li>
          <Link to="/AdminAccounts">👥 {!collapsed && "Accounts"}</Link>
        </li>
        <li>
          <Link to="/adminloan">💵 {!collapsed && "Loans"}</Link>
        </li>
        <li>
          <Link to="">💸 {!collapsed && "Investment"}</Link>
        </li>
        <li>
          <Link to="/CreditCard">💰 {!collapsed && "CreditCard"}</Link>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
