import React from 'react'
import { Accordion, Card, Col, Row } from 'react-bootstrap'
import { Link } from 'react-router-dom'


const VeterinarianCard = ({vet}) => {
  return (
      <Accordion>
        <Accordion.Item>
          <Accordion.Header>
            <Row>
              <Col>
              <Card.Img src={vet.photo} alt='photo' />
              </Col>
              <Col>
                <Card.Title>Dr. {vet.firstName} {vet.lastName}</Card.Title>
                <Card.Title>{vet.specialization}</Card.Title>
                <Card.Title>Reviews: {vet.stars}</Card.Title>
                <Link to={""}>Book Appointment</Link>
              </Col>
            </Row>
          </Accordion.Header>
          <Accordion.Body>
            <div>
              <Link>See what are saying about </Link>{" "}
              <span>Dr. {vet.firstName}</span>
            </div>
          </Accordion.Body>
        </Accordion.Item>
      </Accordion>
  )
}

export default VeterinarianCard