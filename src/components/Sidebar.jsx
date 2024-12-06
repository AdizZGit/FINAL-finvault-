import React, { useState } from "react";
import { Link } from "react-router-dom"; // Import Link for routing
import "../styles.css"; // Updated path

const Sidebar = () => {
  const [collapsed, setCollapsed] = useState(false);
  

  return (
    <div className={`sidebar ${collapsed ? "collapsed" : ""}`}>
      <div className="sidebar-header">
        <h1 className="logo">{!collapsed && "ADMINIS"}</h1>
        <button className="toggle-btn" onClick={() => setCollapsed(!collapsed)}>
          ☰
        </button>
      </div>
      <div className="profile">
        <img
          src="../../assets/logo.avif"
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
          <Link to="/">🏦 {!collapsed && "Dashboard"}</Link>
        </li>
        <li>
          <Link to="/Transactions">📥 {!collapsed && "Transactions"}</Link>
        </li>
        <li>
          <Link to="/Accounts">👥 {!collapsed && "Accounts"}</Link>
        </li>
        <li>
          <Link to="/adminloan">💵 {!collapsed && "Loans"}</Link>
        </li>
        <li>
          <Link to="">💸 {!collapsed && "Contacts Info"}</Link>
        </li>
        <li>
          <Link to="/Investments">💰 {!collapsed && "Credit Card"}</Link>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
