import React, { useState } from "react";
import "./MoneyTransfer.css";

const MoneyTransfer = () => {
  const [formData, setFormData] = useState({
    senderAccount: "",
    recipientAccount: "",
    amount: "",
    transferNote: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`Transaction of $${formData.amount} to account ${formData.recipientAccount} initiated successfully!`);
    setFormData({
      senderAccount: "",
      recipientAccount: "",
      amount: "",
      transferNote: "",
    });
  };

  return (
    <div className="money-transfer-container">
      <h2 className="text-center mb-4">Money Transfer</h2>
      <form className="money-transfer-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="senderAccount">Sender's Account Number</label>
          <input
            type="text"
            id="senderAccount"
            name="senderAccount"
            value={formData.senderAccount}
            onChange={handleChange}
            className="form-control"
            placeholder="Enter sender's account number"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="recipientAccount">Recipient's Account Number</label>
          <input
            type="text"
            id="recipientAccount"
            name="recipientAccount"
            value={formData.recipientAccount}
            onChange={handleChange}
            className="form-control"
            placeholder="Enter recipient's account number"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="amount">Amount</label>
          <input
            type="number"
            id="amount"
            name="amount"
            value={formData.amount}
            onChange={handleChange}
            className="form-control"
            placeholder="Enter amount to transfer"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="transferNote">Transfer Note (Optional)</label>
          <input
            type="text"
            id="transferNote"
            name="transferNote"
            value={formData.transferNote}
            onChange={handleChange}
            className="form-control"
            placeholder="E.g., Rent payment, Gift, etc."
          />
        </div>

        <button type="submit" className="btn btn-success w-100">
          Initiate Transaction
        </button>
      </form>
    </div>
  );
};

export default MoneyTransfer;
