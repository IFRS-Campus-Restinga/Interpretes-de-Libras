import { createAction, createReducer } from "@reduxjs/toolkit";

const initial_state = [
  {
    surdoNome: "Pedro",
    dataEncontro: "23/11/2023",
    hora: "11:00",
    local: "R. Alberto Hoffmann, 285 - Restinga, Porto Alegre - RS, 91791-508a",
  },
];

export const getSolicitacoesAprovadas = createAction(
  "GET_SOLICITACOES_APROVADAS"
);

export default createReducer(initial_state, {
  [getSolicitacoesAprovadas.type]: (state, action) => [...action.payload],
});
