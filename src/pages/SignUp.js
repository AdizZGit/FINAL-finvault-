import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Auth.css";

const SignUp = () => {
  const navigate = useNavigate();
  const [role, setRole] = useState("user"); // Default role
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(""); // State to store error message

  const handleSignUp = async (e) => {
    e.preventDefault();

    const newUser = {
      fullName,
      email,
      password,
      role,
    };

    try {
      const response = await fetch("http://localhost:8080/users/signup", { // Make sure the URL is correct
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newUser),
      });

      if (response.ok) {
        alert("Account created successfully!");
        navigate("/signin"); // Redirect to sign in page after successful signup
      } else {
        const errorMessage = await response.text();
        setError(`Error: ${errorMessage}`);
        alert(`Error: ${errorMessage}`); // Display error message from server
      }
    } catch (error) {
      console.error("Error during sign-up:", error);
      setError("An error occurred while creating the account.");
      alert("An error occurred while creating the account.");
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-form">
        <h2>Create Account</h2>
        <p>Sign up to get started</p>
        <form onSubmit={handleSignUp}>
          {error && <p className="error-message">{error}</p>} {/* Display error message */}
          <div className="form-group">
            <label>Full Name</label>
            <input
              type="text"
              placeholder="Enter your full name"
              required
              value={fullName}
              onChange={(e) => setFullName(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label>Email Address</label>
            <input
              type="email"
              placeholder="Enter your email"
              required
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              placeholder="Create a password"
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label>Role</label>
            <select value={role} onChange={(e) => setRole(e.target.value)} required>
              <option value="user">User</option>
              <option value="admin">Admin</option>
              <option value="employee">Employee</option>
            </select>
          </div>
          <button type="submit" className="auth-btn">Create Account</button>
        </form>
        <div className="auth-footer">
          <p>
            Already have an account?{" "}
            <a href="/signin" className="auth-link">Sign In</a>
          </p>
        </div>
      </div>
    </div>
  );
};

export default SignUp;
