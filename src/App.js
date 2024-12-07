import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import Sidebar from "./components/Sidebar";
import Topbar from "./components/Topbar";
import Dashboard from "./pages/Dashboard";
import CreditCard from "./pages/CreditCard";
// import Accounts from "./pages/Accounts";
import "./styles.css";
import AdminLoanPage from "./pages/adminloan";
import AdminAccounts from "./pages/AdminAccounts";
import Transactions from "./pages/Transactions";

const App = () => {
  return (
    <Router>
      <Routes>
        {/* Redirect root path to /signin */}
        <Route path="/" element={<Navigate to="/signin" />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/signup" element={<SignUp />} />
        
        {/* Main application layout */}
        <Route
          path="*"
          element={
            <div className="app">
              <Sidebar />
              <div className="main-content">
                <Topbar />
                <div className="content">
                  <Routes>
                    <Route path="/dashboard" element={<Dashboard />} />
                    <Route path="/adminaccounts" element={<AdminAccounts />} />
                    <Route path="/contacts" element={<div>Contacts Info Page</div>} />
                    <Route path="/creditcard" element={<CreditCard />} />
                    <Route path="/adminloan" element={<AdminLoanPage />} />
                    <Route path="/Transactions" element={<Transactions />} />
                  </Routes>
                </div>
              </div>
            </div>
          }
        />
      </Routes>
    </Router>
  );
};

export default App;
