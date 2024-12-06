import React, { useState } from "react";
import "./Accounts.css"; // Correct CSS path

const Accounts = () => {
  const [accounts, setAccounts] = useState([
    {
      id: 1,
      name: "Savings Account",
      accountNumber: "1234 **** **** 5678",
      balance: 15250,
      type: "Savings",
      recentActivity: "Deposited $500 on 01 Dec 2024",
    },
    {
      id: 2,
      name: "Checking Account",
      accountNumber: "9876 **** **** 5432",
      balance: 3200,
      type: "Checking",
      recentActivity: "Withdrawn $150 on 30 Nov 2024",
    },
    {
      id: 3,
      name: "Business Account",
      accountNumber: "4567 **** **** 8901",
      balance: 25000,
      type: "Business",
      recentActivity: "Transfer $1,000 on 28 Nov 2024",
    },
  ]);
  const [searchTerm, setSearchTerm] = useState("");
  const [sortOption, setSortOption] = useState("name");
  const [newAccount, setNewAccount] = useState({
    name: "",
    accountNumber: "",
    balance: 0,
    type: "",
    recentActivity: "",
  });

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
  const handleAddAccount = () => {
    setAccounts([
      ...accounts,
      {
        ...newAccount,
        id: accounts.length + 1,
        balance: parseFloat(newAccount.balance),
        recentActivity: "New account added",
      },
    ]);
    setNewAccount({
      name: "",
      accountNumber: "",
      balance: 0,
      type: "",
      recentActivity: "",
    });
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
              <button className="delete-btn">Delete</button>
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
          onChange={(e) =>
            setNewAccount({ ...newAccount, name: e.target.value })
          }
        />
        <input
          type="text"
          placeholder="Account Number"
          value={newAccount.accountNumber}
          onChange={(e) =>
            setNewAccount({ ...newAccount, accountNumber: e.target.value })
          }
        />
        <input
          type="number"
          placeholder="Balance"
          value={newAccount.balance}
          onChange={(e) =>
            setNewAccount({ ...newAccount, balance: e.target.value })
          }
        />
        <select
          value={newAccount.type}
          onChange={(e) =>
            setNewAccount({ ...newAccount, type: e.target.value })
          }
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

export default Accounts;
