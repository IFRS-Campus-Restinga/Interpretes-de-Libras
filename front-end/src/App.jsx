import "./App.css";
import { Routes, Route } from "react-router-dom";
import Header from "./components/header/header";
import Login from "./pages/login/login";
import Cadastro from "./pages/cadastro/cadastro";
import Formulario from "./pages/cadastro/formulario-interprete/formulario";
import Home from "./pages/home/home";
import FormularioSurdo from "./pages/cadastro/formulario-surdo/formulario";
import ListaSolicitacoesDeCadastro from "./pages/listas-solicitacoes-cadastro/lista-solicitacoes-cadastro";
import CadastroDeSolicitacaoDeInteprete from "./pages/cadastro-solicitacao-de-interprete/cadastro-solicitacao-de-interprete";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import HeaderAdmin from "./components/header-admin/header-admin";
import HeaderInterprete from "./components/header-interprete/header-interprete";
import ListaSolicitacoesPublicas from "./pages/lista-solicitacoes-publicas/lsita-solicitacoes-publicas";
import ListaSolicitacoesAprovadas from "./pages/lista-solicitacoes-aprovadas/lista-solicitacoes-aprovadas";
import HeaderSurdo from "./components/header-surdo/header-surdo";
import ListaSolicitacoesIntepretes from "./pages/lista-solicitacoes-interpretes/lista-solicitacoes-interpretes";
import ListaInterpretes from "./pages/lista-interpretes/lista-interpretes";
import PerfilInterprete from "./pages/perfil/usuarios/interprete/perfil_interprete";

function App({ userType }) {
  const renderAdminPages = () => {
    return (
      <>
        <HeaderAdmin />
        <Routes>
          <Route
            path="/lista-solicitacoes-cadastro"
            element={<ListaSolicitacoesDeCadastro />}
          ></Route>
        </Routes>
      </>
    );
  };

  const renderInterpretePages = () => {
    return (
      <>
        <HeaderInterprete />
        <Routes>
          <Route
            path="/lista-solicitacoes-publicas"
            element={<ListaSolicitacoesPublicas />}
          ></Route>
          <Route
            path="/lista-solicitacoes-aprovadas"
            element={<ListaSolicitacoesAprovadas />}
          ></Route>
        </Routes>
      </>
    );
  };

  const renderSurdoPages = () => {
    return (
      <>
        <HeaderSurdo />
        <Routes>
          <Route
            path="/cadastrar-solicitacao-interprete"
            element={<CadastroDeSolicitacaoDeInteprete />}
          ></Route>
          <Route
            path="/minhas-solicitacoes"
            element={<ListaSolicitacoesIntepretes />}
          ></Route>
          <Route
            path="/candidatura-solicitacao"
            element={<ListaInterpretes />}
          ></Route>
        </Routes>
      </>
    );
  };

  const renderDefaultPages = () => {
    return (
      <>
        <Header />
        <Routes>
          <Route path="/" exact element={<Home />}></Route>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/cadastro" element={<Cadastro />}></Route>
          <Route path="/perfil/interprete" element={<PerfilInterprete />}></Route>
          <Route
            path="/cadastro/formulario/surdo"
            element={<FormularioSurdo />}
          ></Route>
          <Route
            path="/cadastro/formulario/interprete"
            element={<Formulario tipoUsuario="interprete" />}
          ></Route>
          <Route
            path="/listaSolicitacoes"
            element={<ListaSolicitacoesDeCadastro />}
          ></Route>
          <Route
            path="/cadastroSolicitacoes"
            element={<CadastroDeSolicitacaoDeInteprete />}
          ></Route>
        </Routes>
      </>
    );
  };
  return (
    <>
      {userType === "ADMIN" && renderAdminPages()}
      {userType === "INTERPRETE" && renderInterpretePages()}
      {userType === "SURDO" && renderSurdoPages()}
      {userType === "default" && renderDefaultPages()}
    </>
  );
}

App.propTypes = {
  userType: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  userType: state.perfil[0].type,
});

export default connect(mapStateToProps)(App);
