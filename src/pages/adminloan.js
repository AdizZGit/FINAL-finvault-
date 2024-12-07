import React, { useState } from "react";
import "./adminloan.css"; // Import the CSS file

const AdminLoanPage = () => {
  // Sample data for loans, including loan amount and account number
  const [loans, setLoans] = useState([
    { id: "1", accountNumber: "123-456-789", type: "Personal Loan", loanAmount: "$30,000", duration: "8 Months", interestRate: 12, isCleared: false, status: "Pending" },
    { id: "2", accountNumber: "999-654-321", type: "Education Loan", loanAmount: "$15,000", duration: "8 Months", interestRate: 10, isCleared: true, status: "Pending" },
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
            <th>Serial No</th> {/* New Serial No column */}
            <th>Account Number</th> {/* New Account Number column */}
            <th>Loan Type</th>
            <th>Loan Amount</th> {/* Loan Amount column */}
            <th>Duration</th>
            <th>Interest Rate</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {loans.map((loan, index) => (
            <tr key={loan.id}>
              <td>{index + 1}</td> {/* Dynamically display serial number */}
              <td>{loan.accountNumber}</td> {/* Display Account Number */}
              <td>{loan.type}</td>
              <td>{loan.loanAmount}</td>
              <td>{loan.duration}</td>
              <td>{loan.interestRate}%</td>
              <td>
                {loan.status === "Pending" ? (
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
                ) : (
                  <span>{loan.status}</span>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminLoanPage;
