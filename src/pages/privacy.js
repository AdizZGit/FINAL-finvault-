import React from "react";
import { Container } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import "./privacy.css";

const Privacy = () => {
  const navigate = useNavigate();

  return (
    <>
      {/* Navbar */}
      <nav className="navbar">
        <div className="navbar-brand">FinVault</div>
        <div className="navbar-links">
          <a href="/">Home</a>
          <a onClick={() => navigate("/signin")} style={{ cursor: "pointer" }}>Login</a>
          <a onClick={() => navigate("/signup")} style={{ cursor: "pointer" }}>Signup</a>
          <a onClick={() => navigate("/privacy")} style={{ cursor: "pointer" }}>Privacy</a>
          <a onClick={() => navigate("/")} style={{ cursor: "pointer" }}>Logout</a>
        </div>
      </nav>

      {/* Privacy Policy Content */}
      <Container className="privacy-container">
        <h1>Privacy Policy</h1>
        <p>
          At FinVault, we are committed to protecting your privacy. This Privacy Policy explains how we collect, use, disclose, and safeguard your information when you visit our website.
        </p>

        <h2>Information We Collect</h2>
        <p>
          We may collect personal identification information (Name, email address, phone number, etc.) and non-personal identification information when you interact with our site.
        </p>

        <h2>How We Use Your Information</h2>
        <ul>
          <li>Provide, operate, and maintain our website</li>
          <li>Improve, personalize, and expand our website</li>
          <li>Understand and analyze how you use our website</li>
          <li>Develop new products, services, features, and functionality</li>
          <li>Send you emails and updates</li>
        </ul>

        <h2>Security of Your Information</h2>
        <p>
          We use administrative, technical, and physical security measures to help protect your personal information.
        </p>

        <h2>Contact Us</h2>
        <p>
          If you have any questions about this Privacy Policy, please contact us at <a href="mailto:support@finvault.com">support@finvault.com</a>.
        </p>
      </Container>
    </>
  );
};

export default Privacy;
