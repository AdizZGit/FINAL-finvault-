import React, { useState } from "react";
import "./MoneyTransfer.css";

const MoneyTransfer = () => {
  const [formData, setFormData] = useState({
    senderAccount: "",
    recipientAccount: "",
    amount: "",
    transferNote: "",
  });

  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    setErrors({ ...errors, [name]: "" }); 
  };

  const validateForm = () => {
    const newErrors = {};
    const accountNumberRegex = /^\d{10,12}$/; // Account number must be 10 to 12 digits

    if (!accountNumberRegex.test(formData.senderAccount)) {
      newErrors.senderAccount = "Sender's account number must be 10 to 12 digits.";
    }

    if (!accountNumberRegex.test(formData.recipientAccount)) {
      newErrors.recipientAccount = "Recipient's account number must be 10 to 12 digits.";
    }

    if (formData.senderAccount === formData.recipientAccount) {
      newErrors.recipientAccount = "Recipient's account number must be different from sender's.";
    }

    if (!formData.amount || parseFloat(formData.amount) <= 0) {
      newErrors.amount = "Amount must be greater than zero.";
    }

    return newErrors;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const newErrors = validateForm();

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      return;
    }

    alert(`Transaction of ₹${formData.amount} to account ${formData.recipientAccount} initiated successfully!`);

    setFormData({
      senderAccount: "",
      recipientAccount: "",
      amount: "",
      transferNote: "",
    });
    setErrors({});
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
          {errors.senderAccount && <p className="text-danger">{errors.senderAccount}</p>}
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
          {errors.recipientAccount && <p className="text-danger">{errors.recipientAccount}</p>}
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
          {errors.amount && <p className="text-danger">{errors.amount}</p>}
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
