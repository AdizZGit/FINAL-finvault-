import React, { useState, useEffect } from "react";
import "./UserAccounts.css";
import axios from "axios";

const UserAccounts = () => {
  const [accounts, setAccounts] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [sortOption, setSortOption] = useState("name");
  const [newAccount, setNewAccount] = useState({
    name: "",
    accountNumber: "",
    balance: "",
    type: "",
  });

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

  const handleAddAccount = async (e) => {
    e.preventDefault();
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
        balance: "",
        type: "",
      });
    } catch (error) {
      console.error("Error adding account:", error);
    }
  };

  const handleDeleteAccount = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/UserAccounts/${id}`);
      setAccounts(accounts.filter((account) => account.id !== id));
    } catch (error) {
      console.error("Error deleting account:", error);
    }
  };

  const filteredAccounts = accounts
    .filter((account) =>
      account.name.toLowerCase().includes(searchTerm.toLowerCase())
    )
    .sort((a, b) =>
      sortOption === "name"
        ? a.name.localeCompare(b.name)
        : b.balance - a.balance
    );

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
        <select onChange={(e) => setSortOption(e.target.value)} value={sortOption}>
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
      <form className="add-account" onSubmit={handleAddAccount}>
        <h2>Add New Account</h2>

        <div className="input-field">
          <input
            type="text"
            placeholder="Account Name"
            value={newAccount.name}
            onChange={(e) => setNewAccount({ ...newAccount, name: e.target.value })}
            required
            pattern="[A-Za-z\s]+"
            title="Account name must contain only alphabet letters."
          />
        </div>

        <div className="input-field">
          <input
            type="text"
            placeholder="Account Number"
            value={newAccount.accountNumber}
            onChange={(e) => setNewAccount({ ...newAccount, accountNumber: e.target.value })}
            required
            pattern="\d{8,16}"
            title="Account number must be 8 to 16 digits."
          />
        </div>

        <div className="input-field">
          <input
            type="number"
            placeholder="Balance"
            value={newAccount.balance}
            onChange={(e) => setNewAccount({ ...newAccount, balance: e.target.value })}
            required
            min="0"
            title="Balance must be a non-negative number."
          />
        </div>

        <div className="input-field">
          <select
            value={newAccount.type}
            onChange={(e) => setNewAccount({ ...newAccount, type: e.target.value })}
            required
            title="Please select an account type."
          >
            <option value="">Select Account Type</option>
            <option value="Savings">Savings</option>
            <option value="Checking">Checking</option>
            <option value="Business">Business</option>
          </select>
        </div>

        <button type="submit">Add Account</button>
      </form>
    </div>
  );
};

export default UserAccounts;
