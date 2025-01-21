import React from 'react'
import { Outlet } from 'react-router-dom'
import BackgroundImageSlider from '../common/BackgroundImageSlider'
import NavbarComponent from './NavbarComponent'
import FooterComponent from './FooterComponent'

const RootLayout = () => {
  return (
    <div>
      <header>
        <NavbarComponent />
      </header>
      <main>
          <Outlet />
      </main>
      <footer>
        <FooterComponent />
      </footer>
    </div>
  )
}

export default RootLayout