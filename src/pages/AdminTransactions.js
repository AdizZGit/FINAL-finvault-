import React, { useState } from 'react';
import { Container, Row, Col, Table, Button, Form, Card } from 'react-bootstrap';

const AdminTransactions = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [sortOrder, setSortOrder] = useState('asc');
  const [filterStatus, setFilterStatus] = useState('All');

  const transactions = [
    { id: '1001', user: 'Rahul Sharma', amount: 45000, status: 'Completed', date: '01/12/2024' },
    { id: '1002', user: 'Priya Verma', amount: 25000, status: 'Pending', date: '02/12/2024' },
    { id: '1003', user: 'Amit Singh', amount: 15000, status: 'Rejected', date: '03/12/2024' },
    { id: '1004', user: 'Neha Mehta', amount: 35000, status: 'Completed', date: '04/12/2024' },
    { id: '1005', user: 'Ravi Kumar', amount: 5000, status: 'Pending', date: '05/12/2024' },
    { id: '1006', user: 'Anjali Gupta', amount: 12000, status: 'Completed', date: '06/12/2024' },
    { id: '1007', user: 'Vikram Chauhan', amount: 8000, status: 'Rejected', date: '07/12/2024' },
    { id: '1008', user: 'Kavita Mishra', amount: 18000, status: 'Pending', date: '08/12/2024' },
  ];

  const totalUsers = 250; // Example total users count
  const totalTransactions = transactions.length;
  const pendingTransactions = transactions.filter((txn) => txn.status === 'Pending').length;

  // Filter, Search, and Sort 
  const filteredTransactions = transactions
    .filter((txn) =>
      (filterStatus === 'All' || txn.status === filterStatus) &&
      txn.user.toLowerCase().includes(searchTerm.toLowerCase())
    )
    .sort((a, b) =>
      sortOrder === 'asc' ? a.date.localeCompare(b.date) : b.date.localeCompare(a.date)
    );

  return (
    <Container fluid className="mt-4">
      <Row className="mb-3">
        <Col>
          <h3>Admin - User Transactions</h3>
        </Col>
      </Row>

      {/* Overview Boxes */}
      <Row className="mb-4">
        <Col md={4}>
          <Card className="p-3 bg-light">
            <h5>Total Users</h5>
            <h3 className="text-primary">{totalUsers}</h3>
          </Card>
        </Col>
        <Col md={4}>
          <Card className="p-3 bg-light">
            <h5>Total Transactions</h5>
            <h3 className="text-info">{totalTransactions}</h3>
          </Card>
        </Col>
        <Col md={4}>
          <Card className="p-3 bg-light">
            <h5>Pending Transactions</h5>
            <h3 className="text-warning">{pendingTransactions}</h3>
          </Card>
        </Col>
      </Row>

      {/* Filter, Search, and Sort Controls */}
      <Row className="mb-3">
        <Col md={4}>
          <Form.Control
            type="text"
            placeholder="Search by user name"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </Col>
        <Col md={2}>
          <Form.Select onChange={(e) => setSortOrder(e.target.value)} value={sortOrder}>
            <option value="asc">Sort by Date (Oldest First)</option>
            <option value="desc">Sort by Date (Newest First)</option>
          </Form.Select>
        </Col>
        <Col md={2}>
          <Form.Select onChange={(e) => setFilterStatus(e.target.value)} value={filterStatus}>
            <option value="All">All Statuses</option>
            <option value="Completed">Completed</option>
            <option value="Pending">Pending</option>
            <option value="Rejected">Rejected</option>
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
                <th>User</th>
                <th>Amount (₹)</th>
                <th>Status</th>
                <th>Date</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {filteredTransactions.map((txn) => (
                <tr key={txn.id}>
                  <td>{txn.id}</td>
                  <td>{txn.user}</td>
                  <td>{`₹${txn.amount.toLocaleString()}`}</td>
                  <td
                    className={
                      txn.status === 'Completed'
                        ? 'text-success'
                        : txn.status === 'Pending'
                        ? 'text-warning'
                        : 'text-danger'
                    }
                  >
                    {txn.status}
                  </td>
                  <td>{txn.date}</td>
                  <td>
                    <Button variant="success" size="sm" className="me-2">
                      Approve
                    </Button>
                    <Button variant="danger" size="sm">
                      Reject
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Col>
      </Row>
    </Container>
  );
};

export default AdminTransactions;
