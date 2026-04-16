import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./PagesCliente/Home/Home";
import Login from "./Login/Login";
import Cadastro from "./Cadastro/Cadastro";
import Gestao from "./GestaoDeResidencia/Gestao";
import Reserva from "./Reserva/Reserva";
<<<<<<< HEAD
import StatusReserva from "./StatusReserva/StatusReserva";
=======
import Residencia from "./Residencia/Residencia";

>>>>>>> 3d3a64259bfaf234d53b72e82b404c92f337e607
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
<<<<<<< HEAD
          <Route path="/status" element={<StatusReserva />} />
=======
          <Route path="/Residencia" element={<Residencia />} />
>>>>>>> 3d3a64259bfaf234d53b72e82b404c92f337e607
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
