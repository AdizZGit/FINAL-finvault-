import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Auth.css";
import ReCAPTCHA from "react-google-recaptcha";

const SignIn = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("user"); // State to store selected role
  const [capVal, setCapVal] = useState(null);

  const handleSignIn = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/users/signin", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password, role }),
      });

      if (response.ok) {
        console.log("Sign-in successful!");
        alert("Sign-in successful!");
        navigate("/dashboard");
      } else {
        const errorMessage = await response.text();
        alert(`Error: ${errorMessage}`);
      }
    } catch (error) {
      console.error("Error during sign-in:", error);
      alert("An error occurred while signing in.");
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-form">
        <h2>Welcome Back</h2>
        <p>Sign in to your account</p>
        <form onSubmit={handleSignIn}>
          <div className="form-group">
            <label>Email Address</label>
            <input
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label>Role</label>
            <select
              value={role}
              onChange={(e) => setRole(e.target.value)}
              required
            >
              <option value="user">User</option>
              <option value="admin">Admin</option>
              <option value="employee">Employee</option>
            </select>
          </div>
          <ReCAPTCHA
            sitekey="6Lcqf44qAAAAAAh7u2ug5Cmc_jts-F1bA6CzkKO6"
            onChange={(val) => setCapVal(val)}
            className="recaptcha-container" // Add class to apply CSS
          />

          <button type="submit" className="auth-btn" disabled={!capVal}>
            Sign In
          </button>
        </form>
        <div className="auth-footer">
          <p>
            Don't have an account?{" "}
            <a href="/signup" className="auth-link">Sign Up</a>
          </p>
        </div>
      </div>
    </div>
  );
};

export default SignIn;
