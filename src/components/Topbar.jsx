import React from "react";
import { Link } from "react-router-dom"; // Removed 'useNavigate'
import "../styles.css"; // Ensure the correct path to your styles.css

const Topbar = ({ onLogout }) => {
  const handleLogout = () => {
    if (onLogout) {
      onLogout(); // Call logout logic passed as a prop
    }
    console.log("User logged out"); // Additional logout logic can go here
  };

  return (
    <div className="topbar">
      {/* Search Bar */}
      <div className="search-bar">
        <input type="text" placeholder="Search" />
        <button>🔍</button>
      </div>

      {/* Topbar Icons */}
      <div className="topbar-icons">
        <button>🔔 Notifications</button>
        <button>⚙️ Settings</button>
        <Link to="/signin" onClick={handleLogout}>
          <button>👤 Logout</button>
        </Link>
      </div>
    </div>
  );
};

export default Topbar;
