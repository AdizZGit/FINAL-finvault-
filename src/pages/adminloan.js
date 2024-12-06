import React, { useState } from "react";
import "./adminloan.css"; // Import the CSS file

const AdminLoanPage = () => {
  // Sample data for loans
  const [loans, setLoans] = useState([
    { id: 1, type: "Personal Loan", duration: "8 Months", interestRate: 12, isCleared: false, status: "Pending" },
    { id: 2, type: "Education Loan", duration: "8 Months", interestRate: 10, isCleared: true, status: "Pending" },
  
  ]);

  // Function to handle approval
  const handleApprove = (id) => {
    setLoans((prevLoans) =>
      prevLoans.map((loan) =>
        loan.id === id ? { ...loan, status: "Approved" } : loan
      )
    );
  };

  // Function to handle rejection
  const handleReject = (id) => {
    setLoans((prevLoans) =>
      prevLoans.map((loan) =>
        loan.id === id ? { ...loan, status: "Rejected" } : loan
      )
    );
  };

  return (
    <div className="admin-loan-page">
      <h1>Admin Loan Management</h1>
      <table className="admin-loan-table">
        <thead>
          <tr>
            <th>Account Number</th>
            <th>Loan Type</th>
            <th>Duration</th>
            <th>Interest Rate</th>
           
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {loans.map((loan) => (
            <tr key={loan.id}>
              <td>{loan.id}</td>
              <td>{loan.type}</td>
              <td>{loan.duration}</td>
              <td>{loan.interestRate}%</td>
             
              <td>
                {loan.status === "Pending" && (
                  <>
                    <button
                      onClick={() => handleApprove(loan.id)}
                      className="approve-btn"
                    >
                      Approve
                    </button>
                    <button
                      onClick={() => handleReject(loan.id)}
                      className="reject-btn"
                    >
                      Reject
                    </button>
                  </>
                )}
                {loan.status === "Approved" && <span>Approved</span>}
                {loan.status === "Rejected" && <span>Rejected</span>}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminLoanPage;
