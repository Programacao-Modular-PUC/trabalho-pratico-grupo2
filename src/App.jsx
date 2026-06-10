import React from "react"; // <-- ESSA LINHA ADICIONADA AQUI EM CIMA É O SEGREDO
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Home from "./PagesCliente/Home/Home";
import Login from "./Login/Login";
import Cadastro from "./Cadastro/Cadastro";
import Gestao from "./GestaoDeResidencia/Gestao";
import Reserva from "./Reserva/Reserva";
import StatusReserva from "./StatusReserva/StatusReserva";
import Residencia from "./Residencia/Residencia";

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
          <Route path="/status" element={<StatusReserva />} />
          <Route path="/Residencia" element={<Residencia />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;