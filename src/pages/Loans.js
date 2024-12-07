import React from "react";
import "./Loans.css";
import { Link } from "react-router-dom";

const Loans = () => {
  const loans = [
    { id: 1, AccountNumber: "bjb44123", LoanType: "Home Loan", leftToRepay: "$30,000", duration: "8 Months" },
    { id: 2, AccountNumber: "xyz456", LoanType: "Education Loan", leftToRepay: "$40,500", duration: "12 Months" },
  ];

  // Function to get the interest rate based on the loan type
  const getInterestRate = (loanType) => {
    if (loanType === "Home Loan") {
      return "12%";
    } else if (loanType === "Education Loan") {
      return "10%";
    }
    return "N/A"; // Default if no loan type matches
  };

  return (
    <div className="loans-container">
      <h2>Loans</h2>

      {/* Loan Summary Section */}
      <div className="loan-summary">
        <div className="loan-card">
          <div>
            <h3>Home Loan</h3>
            <p className="info">
              A Home Loan is a financial product that helps you buy or renovate a home. 
              Typically offered at lower interest rates, home loans provide long-term repayment options. 
              It is ideal for individuals looking to purchase property or renovate their homes. 
              The interest rate is 12%.
            </p>

            <h3>Education Loan</h3>
            <p className="info">
              An Education Loan is designed to help students finance their education, 
              including tuition fees, living expenses, and other related costs. 
              Education loans often have flexible repayment options and are available at competitive interest rates. 
              The interest rate is 10%.
            </p>

            <button className="repay-btn">
              <Link to="/PersonalLoan">Apply for a Loan</Link>
            </button>
          </div>
        </div>
      </div>

      {/* Active Loans Overview */}
      <div className="active-loans">
        <h3>Active Loans Overview</h3>
        <table>
          <thead>
            <tr>
              <th>SL No</th>
              <th>Account Number</th>
              <th>Loan Type</th>
              <th>Left to Repay</th>
              <th>Duration</th>
              <th>Interest Rate</th>
              <th>Repay</th>
            </tr>
          </thead>
          <tbody>
            {loans.map((loan, index) => (
              <tr key={loan.id}>
                <td>{`0${index + 1}`}</td>
                <td>{loan.AccountNumber}</td>
                <td>{loan.LoanType}</td>
                <td>{loan.leftToRepay}</td>
                <td>{loan.duration}</td>
                <td>{getInterestRate(loan.LoanType)}</td> {/* Dynamically set interest rate */}
                <td>
                  <button className="repay-btn2">Repay</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Loans;
