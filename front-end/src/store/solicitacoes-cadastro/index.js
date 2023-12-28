import { createAction, createReducer } from "@reduxjs/toolkit";

const initial_state = [
  /*{
    id: 1,
    nome: "Adrian",
    dataCriacao: "23/05/2023",
    telefone: "51 998765431",
    email: "adrian@restinga.ifrs.edu.br",
    statusAvaliacao: "DEFERIDO",
  },
  {
    id: 2,
    nome: "Pedro",
    dataCriacao: "23/05/2023",
    telefone: "51 998765431",
    email: "adrian@restinga.ifrs.edu.br",
    statusAvaliacao: "INDEFERIDO",
  },
  {
    id: 3,
    nome: "Valentine",
    dataCriacao: "23/05/2023",
    telefone: "51 998765431",
    email: "adrian@restinga.ifrs.edu.br",
    statusAvaliacao: "ANALISANDO",
  },*/
];

export const getAllSolicitacoesCadastros = createAction(
  "ADD_SOLICITACOES_CADASTROS"
);
export const addSolicitacaoCadastro = createAction("ADD_SOLICITACAO_CADASTRO");
export const changeStatusSolicitacaoCadastro = createAction(
  "CHANGE_STATUS_SOLICITACAO"
);

export default createReducer(initial_state, {
  [getAllSolicitacoesCadastros.type]: (state, action) => [...action.payload],
  [addSolicitacaoCadastro.type]: (state, action) => [...state, action.payload],
  [changeStatusSolicitacaoCadastro.type]: (state, action) => [
    ...state,
    action.payload,
  ],
});
