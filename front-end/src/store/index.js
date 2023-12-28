import { configureStore } from "@reduxjs/toolkit";
import thunk from "redux-thunk"; // Importe o middleware thunk
import solicitacoesReducer from "./solicitacoes-cadastro";
import perfil from "./usuario";
import solicitacoesPublicas from "./solicitacoes-publicas";
import solicitacoesAprovadas from "./solicitacoes-aprovadas";
import socicitacoesInterpretes from "./solicitacoes-interpretes";

export default configureStore({
  reducer: {
    solicitacoes: solicitacoesReducer,
    perfil: perfil,
    solicitacoesPublicas: solicitacoesPublicas,
    solicitacoesAprovadas: solicitacoesAprovadas,
    socicitacoesInterpretes: socicitacoesInterpretes,
  },
  middleware: [thunk],
});
