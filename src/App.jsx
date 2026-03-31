import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./PagesCliente/Home/Home";
import Login from './Login/Login'
import Cadastro from "./Cadastro/Cadastro";
function App() {
 

  return (
    <>
     <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
         <Route path="/Login" element={<Login />} />
           <Route path="/Cadastro" element={<Cadastro />} />
       
      </Routes>
    </BrowserRouter>

    </>
  )
}

export default App
