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
        <div className="navbar-brand">MINIBANK</div>
        <div className="navbar-links">
          <a href="#home">Home</a>
          <a onClick={() => navigate("/signin")} style={{ cursor: "pointer" }}>Login</a>
          <a onClick={() => navigate("/signup")} style={{ cursor: "pointer" }}>Signup</a>
          <a href="#privacy">Privacy</a>
          <a onClick={() => navigate("/")} style={{ cursor: "pointer" }}>Logout</a>
        </div>
      </nav>
      <Container className="hero-section text-center">
        <Row className="align-items-center">
          <Col md={6}>
            <img
              src="https://www.shutterstock.com/ima"
              alt="Bank Illustration"
              className="hero-image"
            />
          </Col>
          <Col md={6}>
            <h1>Welcome to FINVAULT</h1>
            <p>
              At FINVAULT, we prioritize your financial success. Our bank offers a
              wide range of services tailored to meet your needs, including
              savings accounts, loans, and investment options. With
              state-of-the-art security and a customer-first approach, we ensure
              your peace of mind and satisfaction.
            </p>
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
