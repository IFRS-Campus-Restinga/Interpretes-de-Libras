import "./App.css";
import { Routes, Route } from "react-router-dom";
import Header from "./components/header/header";
import Login from "./pages/login/login";
import Cadastro from "./pages/cadastro/cadastro";
import Formulario from "./pages/cadastro/formulario/formulario";
import Home from "./pages/homePage/home";

function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/cadastro" element={<Cadastro />}></Route>
        <Route path="/cadastro/formulario" element={<Formulario />}></Route>
      </Routes>
    </>
  );
}

export default App;
