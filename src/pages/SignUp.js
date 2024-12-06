import React from "react";
import { useNavigate } from "react-router-dom";
import "./Auth.css";

const SignUp = () => {
  const navigate = useNavigate();

  const handleSignUp = (e) => {
    e.preventDefault();

    // Simulate successful sign-up logic
    console.log("Account created successfully!");

    // Redirect to the dashboard
    navigate("/dashboard");
  };

  return (
    <div className="auth-container">
      <div className="auth-form">
        <h2>Create Account</h2>
        <p>Sign up to get started</p>
        <form onSubmit={handleSignUp}>
          <div className="form-group">
            <label>Full Name</label>
            <input type="text" placeholder="Enter your full name" required />
          </div>
          <div className="form-group">
            <label>Email Address</label>
            <input type="email" placeholder="Enter your email" required />
          </div>
          <div className="form-group">
            <label>Password</label>
            <input type="password" placeholder="Create a password" required />
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
