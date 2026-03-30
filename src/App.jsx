import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./PagesCliente/Home/Home";
function App() {
 

  return (
    <>
     <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
       
      </Routes>
    </BrowserRouter>

    </>
  )
}

export default App
