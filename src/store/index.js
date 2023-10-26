import { configureStore } from "@reduxjs/toolkit";
import solicitacoesReducer from "./solicitacoes-cadastro";

export default configureStore({
  reducer: {
    solicitacoes: solicitacoesReducer,
  },
});
