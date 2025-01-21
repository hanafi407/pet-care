import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from './component/home/Home';
import RootLayout from './component/layout/RootLayout';
import { createBrowserRouter, createRoutesFromElements, Route, Router, RouterProvider } from 'react-router-dom';

const router = createBrowserRouter([
  {
    path: '/',
    element: <RootLayout />,
    children: [
      { path: '', element: <Home /> }
    ]
  }
]);
const App = () => (
  <RouterProvider router={router} />
)

export default App
