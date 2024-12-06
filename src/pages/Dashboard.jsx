import React from "react";
import "./Dashboard.css";

const Dashboard = () => {
  const accounts = [
    { type: "Savings Account", balance: 15000 },
    { type: "Checking Account", balance: 5000 },
    { type: "Business Account", balance: 30000 },
  ];

  const transactions = [
    { id: 1, description: "Grocery Shopping", amount: -100, date: "Dec 1, 2024" },
    { id: 2, description: "Salary", amount: 3000, date: "Nov 30, 2024" },
    { id: 3, description: "Utility Bill", amount: -150, date: "Nov 28, 2024" },
  ];

  return (
    <div className="dashboard-container">
      <h1>Welcome to Your Dashboard</h1>

      {/* Account Balances */}
      <div className="account-balance-section">
        <h2>Your Accounts</h2>
        <div className="accounts">
          {accounts.map((account, index) => (
            <div key={index} className="account-card">
              <h3>{account.type}</h3>
              <p>Balance: ${account.balance.toLocaleString()}</p>
            </div>
          ))}
        </div>
      </div>

      {/* Recent Transactions */}
      <div className="transactions-section">
        <h2>Recent Transactions</h2>
        <table className="transactions-table">
          <thead>
            <tr>
              <th>Description</th>
              <th>Amount</th>
              <th>Date</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map((transaction) => (
              <tr key={transaction.id}>
                <td>{transaction.description}</td>
                <td className={transaction.amount < 0 ? "negative" : "positive"}>
                  ${Math.abs(transaction.amount).toLocaleString()}
                </td>
                <td>{transaction.date}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Financial Summary */}
      <div className="financial-summary">
        <h2>Financial Summary</h2>
        <div className="summary-cards">
          <div className="summary-card">
            <h3>Total Balance</h3>
            <p>${accounts.reduce((acc, account) => acc + account.balance, 0).toLocaleString()}</p>
          </div>
          <div className="summary-card">
            <h3>Total Transactions</h3>
            <p>{transactions.length}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
