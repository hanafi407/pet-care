import React from "react";
import { Container, Row, Col } from "react-bootstrap";

const FooterComponent = () => {
  return (
    <footer className="bg-dark text-white py-4">
      <Container>
        <Row>
          <Col md={4}>
            <h5>About Us</h5>
            <p>
              We provide the best solutions for your business needs. Connect with us to learn more.
            </p>
          </Col>
          <Col md={4}>
            <h5>Quick Links</h5>
            <ul className="list-unstyled">
              <li>
                <a href="/home" className="text-white text-decoration-none">
                  Home
                </a>
              </li>
              <li>
                <a href="/services" className="text-white text-decoration-none">
                  Services
                </a>
              </li>
              <li>
                <a href="/contact" className="text-white text-decoration-none">
                  Contact
                </a>
              </li>
            </ul>
          </Col>
          <Col md={4}>
            <h5>Contact</h5>
            <p>
              Email: info@example.com <br />
              Phone: +123 456 7890
            </p>
          </Col>
        </Row>
        <Row className="text-center mt-3">
          <Col>
            <p className="mb-0">
              &copy; {new Date().getFullYear()} Your Company. All Rights Reserved.
            </p>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default FooterComponent;
