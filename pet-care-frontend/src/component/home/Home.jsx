import React from 'react'
import d5 from '../../assets/images/d5.jpg'
import { Button, Card, Col, Container, ListGroup, Row } from 'react-bootstrap'
import BackgroundImageSlider from '../common/BackgroundImageSlider'

const Home = () => {
  return (
    <>
      <Container>
        
        <Row>
          <Col>
            <Card>
              <Card.Img src={d5} />
              <Card.Body>
                <Card.Title className='text-info'>Comporehesive Care for your Furry Friend</Card.Title>
                <Card.Text>
                  Lorem ipsum dolor sit amet consectetur adipisicing elit. Reiciendis modi non optio sed odio quasi iure, autem doloremque quae soluta officiis voluptates pariatur dolorem hic quod ullam ipsa, beatae illo!
                </Card.Text>
                <Card.Text>
                  Lorem ipsum dolor sit amet consectetur adipisicing elit. Necessitatibus enim ipsam voluptatibus alias voluptas incidunt. Rem optio non reiciendis dolores magnam, at quis officiis, molestias quam suscipit dicta a amet.
                </Card.Text>
                <Button variant="outline-info" >Meet Our Veterinarian</Button>
              </Card.Body>
            </Card>
          </Col>
          <Col>
            <Card>
              <Card.Img src={d5} />
              <Card.Body>
                <Card.Title className='text-info'>What we do</Card.Title>
                <ListGroup variant='flush' className='mb-3 '>
                  <ListGroup.Item>Item 1</ListGroup.Item>
                  <ListGroup.Item>Item 2</ListGroup.Item>
                  <ListGroup.Item>Item 3</ListGroup.Item>
                  <ListGroup.Item>Item 4</ListGroup.Item>
                </ListGroup>
                <Button variant="outline-info" >Meet Our Veterinarian</Button>
              </Card.Body>

            </Card>
          </Col>
        </Row>
        <Card>
          <Card.Body>
            <Card.Title>What people saying about <span className='text-info'>Universal Pet Care</span> veterinarian. </Card.Title>
          </Card.Body>
          <hr />
          <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Beatae itaque dolorem autem neque, cum maxime numquam illum, quidem deleniti quam aperiam dolor. Officiis rerum omnis corporis quo vitae enim nisi.</p>
        </Card>
      </Container>
    </>
  )
}

export default Home