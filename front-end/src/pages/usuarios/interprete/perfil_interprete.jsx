import "./Perfil_interprete.css";
import HeaderInterprete from "../../../components/header-interprete/header-interprete";
import { Routes, Route } from "react-router-dom";
import ListaSolicitacoesPublicas from "../../lista-solicitacoes-publicas/lsita-solicitacoes-publicas";
import ListaSolicitacoesAprovadas from "../../lista-solicitacoes-aprovadas/lista-solicitacoes-aprovadas";
import InterpreteHomePage from "../interprete-home-page/interprete-home-page";

const PerfilInterprete = () => {
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
        <Route path="/perfil" element={<InterpreteHomePage />}></Route>
      </Routes>
    </>
  );
};

export default PerfilInterprete;
