import { createAction, createReducer } from "@reduxjs/toolkit";

const initial_state = [
  {
    type: "default",
  },
];

export const getUserType = createAction("GET_USER_TYPE");

export default createReducer(initial_state, {
  [getUserType.type]: (state, action) => [...action.payload],
});

//fazer uma action para editar o perfil, ele nao pode ser convertida para ui
//como vamos passar isso para o form? a dúvida é em relacao ao hook do react-form-hook
