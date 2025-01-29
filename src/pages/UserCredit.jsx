import React, { useState } from 'react';
import './UserCredit.css'; 
import axios from 'axios';

export default function UserCredit() {
  // State hooks for form data
  const [cardType, setCardType] = useState('');
  const [nameOnCard, setNameOnCard] = useState('');
  const [applicationDate, setApplicationDate] = useState('');

  // Function to handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent the default form submission behavior

    // Create the payload object
    const formData = {
      cardtype: cardType,
      nameoncard: nameOnCard,
      applicationDate: applicationDate,
    };

    // Send the data to the server using Axios
    try {
      const response = await axios.post('http://localhost:8080/UserCredit', formData);
      console.log('Data saved:', response.data);
      alert('Credit card applied successfully');
    } catch (error) {
      console.log('Error:', error);
      alert('An error occurred while applying for the credit card');
    }
  };

  return (
    <div className="add-card-settings">
      <div className="add-card">
        <h3>Apply for credit card</h3>
        <form onSubmit={handleSubmit}>
          <label>Card Type</label>
          <input
            type="text"
            placeholder="Classic"
            value={cardType}
            onChange={(e) => setCardType(e.target.value)}
          />
          <label>Name On Card</label>
          <input
            type="text"
            placeholder="Enter your Name"
            value={nameOnCard}
            onChange={(e) => setNameOnCard(e.target.value)}
          />
          <label>Application Date</label>
          <input
            type="date"
            value={applicationDate}
            onChange={(e) => setApplicationDate(e.target.value)}
          />
          <button type="submit">Add Card</button>
        </form>
      </div>
      <div className='card-settings'>
        <h3>Your cards</h3>
      </div>
      <div className="card-settings">
        <h3>Card Settings</h3>
        <ul>
          <li>Block Card</li>
          <li>Change Pin Code</li>
        </ul>
      </div>
    </div>
  );
}
