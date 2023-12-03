import HeaderSurdo from "../../../components/header-surdo/header-surdo";
import { Routes, Route } from "react-router-dom";
import CadastroDeSolicitacaoDeInteprete from "../../cadastro-solicitacao-de-interprete/cadastro-solicitacao-de-interprete.jsx";
import ListaSolicitacoesIntepretes from "../../lista-solicitacoes-interpretes/lista-solicitacoes-interpretes";
import ListaInterpretes from "../../lista-interpretes/lista-interpretes.jsx";
import SurdoHomePage from "../surdo-home-page/surdo-home-page";

const PerfilSurdo = () => {
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
        <Route path="/perfil" element={<SurdoHomePage />}></Route>
      </Routes>
    </>
  );
};

export default PerfilSurdo;
