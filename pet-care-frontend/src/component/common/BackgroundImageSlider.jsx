import React, { useState } from 'react'
import bg from '../../assets/images/bg.jpg';
import bg1 from '../../assets/images/bg1.png';
import bg2 from '../../assets/images/bg2.jpg';
import { Carousel } from 'react-bootstrap';

const BackgroundImageSlider = () => {
  const backgrounds = [bg1,bg,bg2];
  const [index, setIndex]= useState(0);
  const handleSelect= (selectIndex)=>{
    setIndex(selectIndex)
  }
  return (
      <Carousel onSelect={handleSelect} activeIndex={index} >
        {backgrounds.map((background,idx)=>(
            <Carousel.Item key={idx}>
              <img
                className="d-block w-100"
                src={background}
                alt="First slide"
              />
            </Carousel.Item>
        ))}
      </Carousel>
       
  )
}

export default BackgroundImageSlider