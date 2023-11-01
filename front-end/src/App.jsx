import "./App.css";
import { Routes, Route } from "react-router-dom";
import Header from "./components/header/header";
import Login from "./pages/login/login";
import Cadastro from "./pages/cadastro/cadastro";
import Formulario from "./pages/cadastro/formulario/formulario";
import Home from "./pages/home/home";
import Perfil from "./pages/perfil/perfil";

function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" exact element={<Home />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/cadastro" element={<Cadastro />}></Route>
        <Route path="/perfil" element={<Perfil />}></Route>
        <Route path="/cadastro/formulario/surdo" element={<Formulario tipoUsuario="surdo"/>}></Route>
        <Route path="/cadastro/formulario/interprete" element={<Formulario tipoUsuario="interprete"/>}></Route>
      </Routes>
    </>
  );
}

export default App;
