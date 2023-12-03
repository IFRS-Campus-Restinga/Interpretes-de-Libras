import { createAction, createReducer } from "@reduxjs/toolkit";

const initial_state = [];

export const setUserType = createAction("SET_USER_TYPE", (tipoUsuario) => ({
  payload: tipoUsuario,
}));
export const getUserType = createAction("GET_USER_TYPE");

export default createReducer(initial_state, {
  [getUserType.type]: (state, action) => [...action.payload],
  [setUserType.type]: (state, action) => [action.payload],
});
