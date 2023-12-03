import { createAction, createReducer } from "@reduxjs/toolkit";

const initial_state = [];

export const getSolicitacoesInterpretes = createAction(
  "GET_SOLICITACOES_INTERPRETES"
);
export const setSolicitacoesInterpretes = createAction(
  "SET_SOLICITACOES_INTERPRETES",
  (solicitacoes) => ({
    payload: solicitacoes,
  })
);

export default createReducer(initial_state, {
  [getSolicitacoesInterpretes.type]: (state, action) => [...action.payload],
  [setSolicitacoesInterpretes.type]: (state, action) => [action.payload],
});
