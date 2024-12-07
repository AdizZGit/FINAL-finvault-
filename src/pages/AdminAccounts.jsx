import React, { useState } from "react";
import "./AdminAccounts.css"; // Admin-specific CSS

const AdminAccounts = () => {
  const [accounts, setAccounts] = useState([
    {
      id: 1,
      name: "John Doe",
      accountNumber: "1234 **** **** 5678",
      balance: 15250,
      type: "Savings",
      recentActivity: "Deposited $500 on 01 Dec 2024",
    },
    {
      id: 2,
      name: "Jane Smith",
      accountNumber: "9876 **** **** 5432",
      balance: 3200,
      type: "Checking",
      recentActivity: "Withdrawn $150 on 30 Nov 2024",
    },
    {
      id: 3,
      name: "ACME Corp",
      accountNumber: "4567 **** **** 8901",
      balance: 25000,
      type: "Business",
      recentActivity: "Transfer $1,000 on 28 Nov 2024",
    },
  ]);
  const [searchTerm, setSearchTerm] = useState("");
  const [sortOption, setSortOption] = useState("name");

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
  const handleAddAccount = (newAccount) => {
    setAccounts([...accounts, { ...newAccount, id: accounts.length + 1 }]);
  };

  // Delete Account
  const handleDeleteAccount = (id) => {
    setAccounts(accounts.filter((account) => account.id !== id));
  };

  return (
    <div className="admin-accounts-container">
      <h1>Admin: Manage Bank Accounts</h1>

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

      {/* Accounts Table */}
      <div className="accounts-table">
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Account Number</th>
              <th>Balance</th>
              <th>Type</th>
              <th>Recent Activity</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredAccounts.map((account) => (
              <tr key={account.id}>
                <td>{account.name}</td>
                <td>{account.accountNumber}</td>
                <td>${account.balance.toLocaleString()}</td>
                <td>{account.type}</td>
                <td>{account.recentActivity}</td>
                <td>
                  <button
                    className="delete-btn"
                    onClick={() => handleDeleteAccount(account.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Add Account Section */}
      <AddAccountForm onAddAccount={handleAddAccount} />
    </div>
  );
};

const AddAccountForm = ({ onAddAccount }) => {
  const [formState, setFormState] = useState({
    name: "",
    accountNumber: "",
    balance: 0,
    type: "",
    recentActivity: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (
      formState.name &&
      formState.accountNumber &&
      formState.balance > 0 &&
      formState.type
    ) {
      onAddAccount({
        ...formState,
        recentActivity: "New account added",
      });
      setFormState({
        name: "",
        accountNumber: "",
        balance: 0,
        type: "",
        recentActivity: "",
      });
    } else {
      alert("Please fill out all fields correctly.");
    }
  };

  return (
    <div className="add-account">
      <h2>Add New Account</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Account Name"
          value={formState.name}
          onChange={(e) =>
            setFormState({ ...formState, name: e.target.value })
          }
          required
        />
        <input
          type="text"
          placeholder="Account Number"
          value={formState.accountNumber}
          onChange={(e) =>
            setFormState({ ...formState, accountNumber: e.target.value })
          }
          required
        />
        <input
          type="number"
          placeholder="Balance"
          value={formState.balance}
          onChange={(e) =>
            setFormState({ ...formState, balance: parseFloat(e.target.value) })
          }
          required
        />
        <select
          value={formState.type}
          onChange={(e) =>
            setFormState({ ...formState, type: e.target.value })
          }
          required
        >
          <option value="">Select Account Type</option>
          <option value="Savings">Savings</option>
          <option value="Checking">Checking</option>
          <option value="Business">Business</option>
        </select>
        <button type="submit">Add Account</button>
      </form>
    </div>
  );
};

export default AdminAccounts;
