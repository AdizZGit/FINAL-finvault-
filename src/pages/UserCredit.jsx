import React from 'react';
import './UserCredit.css'; 

export default function UserCredit() {
  return (
    <div>
      <div className="add-card-settings">
        <div className="add-card">
          <h3>Apply for credit card</h3>
          <form>
            <label>Card Type</label>
            <input type="text" placeholder="Classic" />
            <label>Name On Card</label>
            <input type="text" placeholder="Enter your Name" />
            <input type="date" />
            <button type="submit">Add Card</button>
          </form>
        </div>
        <div className='card-settings'>
          <h3>Your cards</h3>
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
