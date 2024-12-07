import React, { useState } from 'react';
import './UserCredit.css';

export default function UserCredit() {
  const [cards, setCards] = useState([]);
  const [cardDetails, setCardDetails] = useState({
    cardType: '',
    nameOnCard: '',
    expiryDate: '',
  });

  // Handle input changes
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCardDetails({ ...cardDetails, [name]: value });
  };

  // Handle form submission
  const handleAddCard = (e) => {
    e.preventDefault();

    // Validate form
    if (!cardDetails.cardType || !cardDetails.nameOnCard || !cardDetails.expiryDate) {
      alert('Please fill out all fields.');
      return;
    }

    // Add the new card to the list
    setCards([...cards, cardDetails]);

    // Reset form
    setCardDetails({
      cardType: '',
      nameOnCard: '',
      expiryDate: '',
    });
  };

  return (
    <div>
      <div className="add-card-settings">
        <div className="add-card">
          <h3>Apply for Credit Card</h3>
          <form onSubmit={handleAddCard}>
            <label>Card Type</label>
            <input
              type="text"
              name="cardType"
              value={cardDetails.cardType}
              onChange={handleInputChange}
              placeholder="Classic"
            />
            <label>Name on Card</label>
            <input
              type="text"
              name="nameOnCard"
              value={cardDetails.nameOnCard}
              onChange={handleInputChange}
              placeholder="Enter your Name"
            />
            <label>Expiry Date</label>
            <input
              type="date"
              name="expiryDate"
              value={cardDetails.expiryDate}
              onChange={handleInputChange}
            />
            <button type="submit">Add Card</button>
          </form>
        </div>
        <div className="card-settings">
          <h3>Your Cards</h3>
          <ul>
            {cards.length > 0 ? (
              cards.map((card, index) => (
                <li key={index}>
                  <strong>{card.cardType}</strong> - {card.nameOnCard} (Expires: {card.expiryDate})
                </li>
              ))
            ) : (
              <p>No cards added yet.</p>
            )}
          </ul>
        </div>
      </div>
      <div className="card-settings-1">
        <h3>Card Settings</h3>
        <ul>
          <li>Block Card</li>
          <li>Change Pin Code</li>
        </ul>
      </div>
    </div>
  );
}
