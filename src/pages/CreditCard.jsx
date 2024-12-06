import React, { useState, useEffect } from "react";
import "./CreditCard.css";

export default function CreditCard() {
  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const mockApplications = [
      { id: 1, name: "Udayraj Ghuge", email: "udayraj@gmail.com", income: 40000, creditScore: 720, status: "Pending" },
      { id: 2, name: "Chetan Kirange", email: "Chetan@gmail.com", income: 25000, creditScore: 680, status: "Pending" },
    ];
    setTimeout(() => {
      setApplications(mockApplications);
      setLoading(false);
    }, 1000);
  }, []);

  return (
    <div className="admin-container">
      <h1>Admin Credit Card Approvals</h1>
      <table className="approval-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Income</th>
            <th>Credit Score</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {applications.map((app) => (
            <tr key={app.id}>
              <td>{app.name}</td>
              <td>{app.email}</td>
              <td>${app.income}</td>
              <td>{app.creditScore}</td>
              <td>{app.status}</td>
              <td>
                <button className="approve-btn">Approve</button>
                <button className="reject-btn">Reject</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}