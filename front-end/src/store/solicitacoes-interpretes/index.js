import { createAction, createReducer } from "@reduxjs/toolkit";

const initial_state = [
  {
    dataEncontro: "23/11/2023",
    hora: "11:00",
    local: "R. Alberto Hoffmann, 285 - Restinga, Porto Alegre - RS, 91791-508a",
    interpretes: [
      {
        nome: "Valentine",
        avaliacao: 5,
      },
      {
        nome: "Pedro",
        avaliacao: 5,
      },
    ],
  },
];

export const getSolicitacoesInterpretes = createAction(
  "GET_SOLICITACOES_INTERPRETES"
);

export default createReducer(initial_state, {
  [getSolicitacoesInterpretes.type]: (state, action) => [...action.payload],
});
