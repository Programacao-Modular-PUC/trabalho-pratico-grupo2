import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./PagesCliente/Home/Home";
import Login from "./Login/Login";
import Cadastro from "./Cadastro/Cadastro";
import Gestao from "./GestaoDeResidencia/Gestao";
import Reserva from "./Reserva/Reserva";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/Login" element={<Login />} />
          <Route path="/Cadastro" element={<Cadastro />} />
          <Route path="/Gestao" element={<Gestao />} />
          <Route path="/reserva" element={<Reserva />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
