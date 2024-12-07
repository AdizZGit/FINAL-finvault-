import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // To handle navigation
import "../styles.css"; // Ensure the correct path to your styles.css

const Topbar = ({ onLogout }) => {
  const [darkMode, setDarkMode] = useState(false);
  const navigate = useNavigate();

  const toggleDarkMode = () => {
    setDarkMode(!darkMode);
    document.documentElement.classList.toggle("dark"); // Toggle a class on the <html> tag for dark mode
  };

  const handleLogout = () => {
    onLogout(); // Call the logout function passed from the parent
    navigate("/signin"); // Redirect to the signin page
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
        <button onClick={toggleDarkMode}>
          {darkMode ? "🌞 Light Mode" : "🌙 Dark Mode"}
        </button>
        <button>🔔 Notifications</button>
        <button>⚙️ Settings</button>
        <button onClick={handleLogout}>👤 Logout</button>
      </div>
    </div>
  );
};

export default Topbar;
