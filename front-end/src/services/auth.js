import api from "./api";

const loginUser = async (data) => {
  const login = JSON.stringify(data);
  const loginString = JSON.parse(login);
  const payloadString = loginString["payload"];
  console.log(payloadString);
  try {
    const response = await api.post("/login", payloadString);
    const token = response.data.token;
    saveTokenLocally(token);
    return token;
  } catch (error) {
    console.error("Erro de login:", error);
    throw error;
  }
};

const saveTokenLocally = (token) => {
  localStorage.setItem("token", token);
};

const getToken = () => {
  return localStorage.getItem("token");
};

export { loginUser, getToken, saveTokenLocally };
