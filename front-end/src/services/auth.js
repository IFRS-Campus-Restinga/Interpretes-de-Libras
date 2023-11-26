import React from "react";
import api from "./api";
import { setUserType } from "../store/usuario";
import { useDispatch } from "react-redux";

const loginUser = async (data, dispatch) => {
  //const dispatch = useDispatch();
  const login = JSON.stringify(data);
  const loginString = JSON.parse(login);
  const payloadString = loginString["payload"];

  console.log(payloadString);

  try {
    const response = await api.post("/login", payloadString);
    console.log("response", response);

    const token = response.data.token;
    saveTokenLocally(response.data);
    dispatch(setUserType(response.data));

    return token;
  } catch (error) {
    console.error("Erro de login:", error);
    throw error;
  }
};

const saveTokenLocally = (data) => {
  localStorage.setItem("token", data.token);
  localStorage.setItem("tipoUsuario", data.tipoUsuario);
  if (data.tipoUsuario === "INTERPRETE") {
    localStorage.setItem("idInterprete", data.id);
  } else if (data.tipoUsuario === "SURDO") {
    localStorage.setItem("idSurdo", data.id);
  }
};

const getToken = () => {
  return localStorage.getItem("token");
};

export { loginUser, getToken, saveTokenLocally };
