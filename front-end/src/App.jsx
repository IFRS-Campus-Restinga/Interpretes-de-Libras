import "./App.css";
import { Routes, Route } from "react-router-dom";
import Login from "./pages/login/login";
import Cadastro from "./pages/cadastro/cadastro";
import Formulario from "./pages/cadastro/formulario-interprete/formulario";
import Home from "./pages/home/home";
import FormularioSurdo from "./pages/cadastro/formulario-surdo/formulario";
import PerfilAdmin from "../src/pages/usuarios/admin/perfil-admin";
import PerfilInterprete from "../src/pages/usuarios/interprete/perfil_interprete";
import PerfilSurdo from "../src/pages/usuarios/surdo/perfil_surdo.jsx";
import ForgotPassword from "../src/pages/login/forgotPassword/recupera_senha.jsx";

function App() {
  return (
    <Routes>
      <Route path="/" exact element={<Home />}></Route>
      <Route path="/login" element={<Login />}></Route>
      <Route path="/cadastro" element={<Cadastro />}></Route>
      <Route path="/login/forgotPassword" element={<ForgotPassword />}></Route>
      <Route
        path="/cadastro/formulario/surdo"
        element={<FormularioSurdo />}
      ></Route>
      <Route
        path="/cadastro/formulario/interprete"
        element={<Formulario />}
      ></Route>
      <Route path="/admin/*" element={<PerfilAdmin />} />
      <Route path="/inteprete/*" element={<PerfilInterprete />}></Route>
      <Route path="/surdo/*" element={<PerfilSurdo />}></Route>
    </Routes>
  );
}

export default App;
