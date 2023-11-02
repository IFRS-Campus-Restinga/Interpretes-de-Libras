import { createAction, createReducer } from "@reduxjs/toolkit";

const initial_state = [
  {
    nome: "adrian",
    endereco: "Rua TANANA, 2020. POA/RS",
    telefone: "51 998765431",
    email: "adrian@restinga.ifrs.edu.br",
  },
  {
    nome: "pedro",
    endereco: "Rua TANANA, 2020. POA/RS",
    telefone: "51 998765431",
    email: "pedro@restinga.ifrs.edu.br",
  },
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

// //reducer
// export default function reducer(state = initial_state, action) {
//   if (action.type === "ADD_SOLICITACAO_CADASTRO") {
//     return [...state, action.solicitacao];
//   }
//   return state;
// }

// //action
// export const addSolicitacaoCadastro = (solicitacao) => {
//   return {
//     type: "ADD_SOLICITACAO_CADASTRO",
//     //talvez colocar payload: solicitacao,
//     solicitacao,
//   };
// };

//refactor
