import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

export default function CreditCard() {
  const [applications, setApplications] = useState([]);

  useEffect(() => {
    const mockApplications = [
      { id: 1, name: "Udayraj Ghuge", email: "udayraj@gmail.com", income: 40000, creditScore: 720, status: "Pending" },
      { id: 2, name: "Chetan Kirange", email: "chetan@gmail.com", income: 25000, creditScore: 680, status: "Pending" },
      { id: 3, name: "Aditya", email: "aditya@gmail.com", income: 35000, creditScore: 400, status: "Pending" },
      { id: 4, name: "Ruchita", email: "Ruchita@gmail.com", income: 45000, creditScore: 750, status: "Pending" },
      { id: 3, name: "Aditya", email: "aditya@gmail.com", income: 35000, creditScore: 400, status: "Pending" },
    ];
    setApplications(mockApplications);
  }, []);

  return (
    <div className="container mt-5">
      <div className="text-center mb-4 border p-3 rounded shadow">
        <h1>Admin Credit Card Approvals</h1>
      

      {/* Card Component */}
      <div className="card mb-4">
        <div className="card-body">
          <h5 className="card-title">Approval Guidelines</h5>
          <p className="card-text">
            Review applications based on the credit score and income provided. Approve or reject accordingly.
          </p>
        </div>
      </div>

      {/* Table Component */}
      <div className="table-responsive">
        <table className="table">
          <thead className="thead-dark">
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
                <td>₹{app.income.toLocaleString()}</td>
                <td>{app.creditScore}</td>
                <td>{app.status}</td>
                <td>
                  <button className="btn btn-success btn-sm me-2">Approve</button>
                  <button className="btn btn-danger btn-sm me-2">Reject</button>
                  <button className="btn btn-primary btn-sm">Send Message</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      </div>
    </div>
  );
}
