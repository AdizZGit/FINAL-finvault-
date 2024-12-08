import React, { useState, useEffect } from "react";
import "./UserAccounts.css"; 
import axios from "axios"; // For making HTTP requests

const UserAccounts = () => {
  const [accounts, setAccounts] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [sortOption, setSortOption] = useState("name");
  const [newAccount, setNewAccount] = useState({
    name: "",
    accountNumber: "",
    balance: 0,
    type: "",
    recentActivity: "",
  });

  // Fetch accounts from the backend on component mount
  useEffect(() => {
    fetchAccounts();
  }, []);

  const fetchAccounts = async () => {
    try {
      const response = await axios.get("http://localhost:8080/UserAccounts");
      setAccounts(response.data);
    } catch (error) {
      console.error("Error fetching accounts:", error);
    }
  };

  // Filter and Sort Accounts
  const filteredAccounts = accounts
    .filter((account) =>
      account.name.toLowerCase().includes(searchTerm.toLowerCase())
    )
    .sort((a, b) =>
      sortOption === "name"
        ? a.name.localeCompare(b.name)
        : b.balance - a.balance
    );

  // Add New Account
  const handleAddAccount = async () => {
    try {
      const response = await axios.post("http://localhost:8080/UserAccounts", {
        ...newAccount,
        balance: parseFloat(newAccount.balance),
        recentActivity: "New account added",
      });
      setAccounts([...accounts, response.data]);
      setNewAccount({
        name: "",
        accountNumber: "",
        balance: 0,
        type: "",
        recentActivity: "",
      });
    } catch (error) {
      console.error("Error adding account:", error);
    }
  };

  // Delete Account
  const handleDeleteAccount = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/UserAccounts/${id}`);
      setAccounts(accounts.filter((account) => account.id !== id));
    } catch (error) {
      console.error("Error deleting account:", error);
    }
  };

  return (
    <div className="accounts-container">
      <h1>My Bank Accounts</h1>

      {/* Search and Sort Section */}
      <div className="controls">
        <input
          type="text"
          placeholder="Search accounts..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <select onChange={(e) => setSortOption(e.target.value)}>
          <option value="name">Sort by Name</option>
          <option value="balance">Sort by Balance</option>
        </select>
      </div>

      {/* Account Cards */}
      <div className="accounts-list">
        {filteredAccounts.map((account) => (
          <div key={account.id} className="account-card">
            <h3>{account.name}</h3>
            <p>{account.accountNumber}</p>
            <p className="balance">Balance: ${account.balance.toLocaleString()}</p>
            <p className="recent-activity">{account.recentActivity}</p>
            <div className="actions">
              <button className="view-btn">View Details</button>
              <button className="delete-btn" onClick={() => handleDeleteAccount(account.id)}>
                Delete
              </button>
            </div>
          </div>
        ))}
      </div>

      {/* Add New Account Section */}
      <div className="add-account">
        <h2>Add New Account</h2>
        <input
          type="text"
          placeholder="Account Name"
          value={newAccount.name}
          onChange={(e) => setNewAccount({ ...newAccount, name: e.target.value })}
        />
        <input
          type="text"
          placeholder="Account Number"
          value={newAccount.accountNumber}
          onChange={(e) => setNewAccount({ ...newAccount, accountNumber: e.target.value })}
        />
        <input
          type="number"
          placeholder="Balance"
          value={newAccount.balance}
          onChange={(e) => setNewAccount({ ...newAccount, balance: e.target.value })}
        />
        <select
          value={newAccount.type}
          onChange={(e) => setNewAccount({ ...newAccount, type: e.target.value })}
        >
          <option value="">Select Account Type</option>
          <option value="Savings">Savings</option>
          <option value="Checking">Checking</option>
          <option value="Business">Business</option>
        </select>
        <button onClick={handleAddAccount}>Add Account</button>
      </div>
    </div>
  );
};

export default UserAccounts;
