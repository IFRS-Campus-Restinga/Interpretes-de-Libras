import "./Perfil_interprete.css";
import { Routes, Route } from "react-router-dom";
import HeaderAdmin from "../../../components/header-admin/header-admin";
import ListaSolicitacoesDeCadastro from "../../listas-solicitacoes-cadastro/lista-solicitacoes-cadastro";

const PerfilAdmin = () => {
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

export default PerfilAdmin;
