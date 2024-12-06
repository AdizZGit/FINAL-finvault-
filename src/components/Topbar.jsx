import React, { useState } from "react";
import "../styles.css"; // Ensure the correct path to your styles.css

const Topbar = () => {
  const [darkMode, setDarkMode] = useState(false);

  const toggleDarkMode = () => {
    setDarkMode(!darkMode);
    document.documentElement.classList.toggle("dark"); // Toggle a class on the <html> tag for dark mode
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
          {darkMode ? "🌞" : "🌙"} {/* Toggle between light and dark icons */}
        </button>
        <button>🔔</button>
        <button>⚙️</button>
        <button>👤</button>
      </div>
    </div>
  );
};

export default Topbar;
