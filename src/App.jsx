import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./PagesCliente/Home/Home";
import Login from './Login/Login'
function App() {
 

  return (
    <>
     <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
         <Route path="/Login" element={<Login />} />
       
      </Routes>
    </BrowserRouter>

    </>
  )
}

export default App
