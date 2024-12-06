import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Sidebar from "./components/Sidebar";
import Topbar from "./components/Topbar";
import Dashboard from "./pages/Dashboard";
import CreditCard from "./pages/CreditCard";
import Accounts from "./pages/Accounts";
import "./styles.css";
import AdminLoanPage from "./pages/adminloan";


const App = () => {
  return (
    <Router>
      <div className="app">
        <Sidebar />
        <div className="main-content">
          <Topbar />
          <div className="content">
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/accounts" element={<Accounts />} />
              <Route path="/contacts" element={<div>Contacts Info Page</div>} />
              <Route path="/CreditCard" element={<CreditCard />} />
              <Route path="/adminloan" element={<AdminLoanPage />} />
            
            </Routes>
          </div>
        </div>
      </div>
    </Router>
  );
};

export default App;
