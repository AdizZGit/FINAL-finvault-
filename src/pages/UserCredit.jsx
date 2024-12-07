import React, { useState } from "react";
import "./UserCredit.css";

export default function UserCredit() {
  const [cards, setCards] = useState([]);
  const [cardDetails, setCardDetails] = useState({
    cardType: "",
    nameOnCard: "",
    expiryDate: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCardDetails({ ...cardDetails, [name]: value });
  };

  const handleAddCard = (e) => {
    e.preventDefault();
    if (!cardDetails.cardType || !cardDetails.nameOnCard || !cardDetails.expiryDate) {
      alert("Please fill out all fields.");
      return;
    }
    setCards([...cards, cardDetails]);
    setCardDetails({ cardType: "", nameOnCard: "", expiryDate: "" });
  };

  return (
    <div className="user-credit-container">
      <header className="header">
        <h1>Credit Card Dashboard</h1>
        <p>Manage your cards and transactions seamlessly</p>
      </header>
      <div className="dashboard">
        <div className="add-card-section">
          <h2>Apply for a New Card</h2>
          <form onSubmit={handleAddCard} className="card-form">
            <label>Card Type</label>
            <input
              type="text"
              name="cardType"
              value={cardDetails.cardType}
              onChange={handleInputChange}
              placeholder="e.g., Visa, Mastercard"
              className="input-field"
            />
            <label>Name on Card</label>
            <input
              type="text"
              name="nameOnCard"
              value={cardDetails.nameOnCard}
              onChange={handleInputChange}
              placeholder="Your Name"
              className="input-field"
            />
            <label>Expiry Date</label>
            <input
              type="date"
              name="expiryDate"
              value={cardDetails.expiryDate}
              onChange={handleInputChange}
              className="input-field"
            />
            <button type="submit" className="submit-btn">Add Card</button>
          </form>
        </div>

        <div className="cards-section">
          <h2>Your Cards</h2>
          <div className="cards-display">
            {cards.length > 0 ? (
              cards.map((card, index) => (
                <div key={index} className="card">
                  <div className="card-gradient-overlay"></div>
                  <div className="card-content">
                    <div className="card-chip"></div>
                    <h3>{card.cardType}</h3>
                    <p>{card.nameOnCard}</p>
                    <p>Expires: {card.expiryDate}</p>
                    <div className="card-footer">
                      <p>•••• •••• •••• 1234</p>
                      <img
                        src={
                          card.cardType.toLowerCase() === "visa"
                            ? "https://upload.wikimedia.org/wikipedia/commons/5/5e/Visa_2021.svg"
                            : "https://upload.wikimedia.org/wikipedia/commons/a/a4/Mastercard_2019_logo.svg"
                        }
                        alt="Card Logo"
                        className="card-logo"
                      />
                    </div>
                  </div>
                </div>
              ))
            ) : (
              <p>No cards added yet. Apply for a new card!</p>
            )}
          </div>
        </div>
      </div>

      <footer className="footer">
        <p>© 2024 Credit Dashboard. All Rights Reserved.</p>
      </footer>
    </div>
  );
}
