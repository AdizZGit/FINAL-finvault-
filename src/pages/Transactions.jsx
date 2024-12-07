import React, { useState } from 'react';
import { Container, Row, Col, Table, Button, Form, Modal, Card } from 'react-bootstrap';
import MoneyTransfer from '../components/MoneyTransfer';
import 'bootstrap/dist/css/bootstrap.min.css';


const Transactions = () => {
  const [showModal, setShowModal] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  const [sortOrder, setSortOrder] = useState('asc');

  const transactions = [
    { id: '1001', name: 'Rahul Sharma', amount: 45000, status: 'Completed', date: '01/12/2024', attachment: 'receipt1.pdf' },
    { id: '1002', name: 'Priya Verma', amount: 25000, status: 'Pending', date: '02/12/2024', attachment: 'receipt2.pdf' },
    { id: '1003', name: 'Amit Singh', amount: 15000, status: 'Completed', date: '03/12/2024', attachment: 'receipt3.pdf' },
    { id: '1004', name: 'Neha Mehta', amount: 35000, status: 'Completed', date: '04/12/2024', attachment: 'receipt4.pdf' },
    { id: '1005', name: 'Ravi Kumar', amount: 5000, status: 'Pending', date: '05/12/2024', attachment: 'receipt5.pdf' },
    { id: '1006', name: 'Anjali Gupta', amount: 12000, status: 'Completed', date: '06/12/2024', attachment: 'receipt6.pdf' },
    { id: '1007', name: 'Vikram Chauhan', amount: 8000, status: 'Completed', date: '07/12/2024', attachment: 'receipt7.pdf' },
    { id: '1008', name: 'Kavita Mishra', amount: 18000, status: 'Pending', date: '08/12/2024', attachment: 'receipt8.pdf' },
  ];

  const accountBalance = 150000; // Example balance in Rupees
  const totalIncome = 200000;
  const totalExpense = 50000;

  // Filter transactions by name
  const filteredTransactions = transactions
    .filter((transaction) =>
      transaction.name.toLowerCase().includes(searchTerm.toLowerCase())
    )
    .sort((a, b) =>
      sortOrder === 'asc'
        ? a.name.localeCompare(b.name)
        : b.name.localeCompare(a.name)
    );

  return (
    <Container fluid className="mt-4">
      <Row className="mb-3">
        <Col>
          <h3>My Transactions</h3>
        </Col>
        <Col className="text-end">
          <Button variant="primary" onClick={() => setShowModal(true)}>
            Make Transaction
          </Button>
        </Col>
      </Row>

      {/* Account Balance and Overview Box */}
      <Row className="mb-4">
        <Col md={4}>
          <Card className="p-3 bg-light">
            <h5>Account Balance</h5>
            <h3 className="text-primary">₹{accountBalance.toLocaleString()}</h3>
          </Card>
        </Col>
        <Col md={4}>
          <Card className="p-3 bg-light">
            <h5>Total Income</h5>
            <h3 className="text-success">₹{totalIncome.toLocaleString()}</h3>
          </Card>
        </Col>
        <Col md={4}>
          <Card className="p-3 bg-light">
            <h5>Total Expenses</h5>
            <h3 className="text-danger">₹{totalExpense.toLocaleString()}</h3>
          </Card>
        </Col>
      </Row>

      {/* Search and Sort Controls */}
      <Row className="mb-3">
        <Col md={4}>
          <Form.Control
            type="text"
            placeholder="Search by name"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </Col>
        <Col md={2}>
          <Form.Select onChange={(e) => setSortOrder(e.target.value)} value={sortOrder}>
            <option value="asc">Sort by Name (A-Z)</option>
            <option value="desc">Sort by Name (Z-A)</option>
          </Form.Select>
        </Col>
      </Row>

      {/* Transactions Table */}
      <Row>
        <Col>
          <Table striped bordered hover responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Amount (₹)</th>
                <th>Status</th>
                <th>Date</th>
                <th>Attachment</th>
              </tr>
            </thead>
            <tbody>
              {filteredTransactions.map((transaction) => (
                <tr key={transaction.id}>
                  <td>{transaction.id}</td>
                  <td>{transaction.name}</td>
                  <td>{`₹${transaction.amount.toLocaleString()}`}</td>
                  
                  <td className={transaction.status === 'Completed' ? 'text-success' : 'text-danger'}>
                    {transaction.status}
                  </td>
                  <td>{transaction.date}</td>
                  <td>
                    <a href={`/attachments/₹{transaction.attachment}`} target="_blank" rel="noopener noreferrer">
                      Receipt
                    </a>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Col>
      </Row>

      {/* Modal for Transaction Form */}
      <Modal show={showModal} onHide={() => setShowModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Make Transaction</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <MoneyTransfer />
        </Modal.Body>
      </Modal>
    </Container>
  );
};

export default Transactions;
