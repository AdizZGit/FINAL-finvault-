// src/components/Home.js
import React from "react";
import { Container, Button, Row, Col } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import "./home.css";

const Home = () => {
  const navigate = useNavigate();

  return (
    <div className="home-container">
      <nav className="navbar">
        <div className="navbar-brand">FinVault</div>
        <div className="navbar-links">
          <a href="#home">Home</a>
          <a onClick={() => navigate("/signin")} style={{ cursor: "pointer" }}>Login</a>
          <a onClick={() => navigate("/signup")} style={{ cursor: "pointer" }}>Signup</a>
          <a onClick={() => navigate("/privacy")} style={{ cursor: "pointer" }}>Privacy</a>
          <a onClick={() => navigate("/")} style={{ cursor: "pointer" }}>Logout</a>
        </div>
      </nav>
      <Container className="hero-section text-center">
        <Row className="align-items-center">
        <Col md={6}>
            <img
              src="/assets/bank.jpeg"
              alt="Bank Illustration"
              className="hero-image"
            />
          </Col>
          <Col md={6}>
            <h1>Welcome to FinVault Banking</h1>
            <p>
            At FinVault, we are committed to empowering your financial journey with cutting-edge banking solutions. Our services include secure savings accounts, seamless fund transfers, and hassle-free loan options. With advanced security measures and a customer-centric approach, we ensure your banking experience is smooth, reliable, and rewarding.</p>

            <Button className="get-started-btn" variant="primary" onClick={() => navigate("/signup")}>
              Get Started
            </Button>
          </Col>
        </Row>
      </Container>
      <footer className="footer">
        <p>
          &copy; 2025 - MINIBANK - <a href="#privacy">Privacy</a>
        </p>
      </footer>
    </div>
  );
};

export default Home;
