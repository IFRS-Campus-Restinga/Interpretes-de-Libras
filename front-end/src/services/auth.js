import React from "react";
import api from "./api";
import { setUserType } from "../store/usuario";

const loginUser = async (data, dispatch) => {
  const login = JSON.stringify(data);
  const loginString = JSON.parse(login);
  const payloadString = loginString["payload"];

  try {
    const response = await api.post("/login", payloadString);
    saveTokenLocally(response.data);
    dispatch(setUserType(response.data));
  } catch (error) {
    console.error("Erro de login:", error);
    throw error;
  }
};

const saveTokenLocally = (data) => {
  localStorage.setItem("token", data.token);
  localStorage.setItem("tipoUsuario", data.tipoUsuario);
  localStorage.setItem("id", data.id);
};

const getToken = () => {
  return localStorage.getItem("token");
};

export { loginUser, getToken, saveTokenLocally };
