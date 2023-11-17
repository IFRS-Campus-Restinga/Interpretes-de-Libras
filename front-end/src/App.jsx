import "./App.css";
import { Routes, Route } from "react-router-dom";
import Header from "./components/header/header";
import Login from "./pages/login/login";
import Cadastro from "./pages/cadastro/cadastro";
import Formulario from "./pages/cadastro/formulario/formulario";
import Home from "./pages/home/home";
import Perfil from "./pages/perfil/perfil";
import FormularioSurdo from "./pages/cadastro/formulario-surdo/formulario";
import ListaSolicitacoes from "./pages/listas/lista-solicitacoes-cadastro";
import FormularioSolicitacao from "./pages/cadastro-solicitacao-de-interprete/cadastro-solicitacao-de-interprete";

function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" exact element={<Home />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/cadastro" element={<Cadastro />}></Route>
        <Route path="/perfil" element={<Perfil />}></Route>
        <Route path="/cadastro/formulario/surdo" element={<FormularioSurdo/>}></Route>
        <Route path="/cadastro/formulario/interprete" element={<Formulario tipoUsuario="interprete"/>}></Route>
        <Route path="/listaSolicitacoes" element={<ListaSolicitacoes />}></Route>
        <Route path="/cadastroSolicitacoes" element={<FormularioSolicitacao />}></Route>
      </Routes>
    </>
  );
}

export default App;
