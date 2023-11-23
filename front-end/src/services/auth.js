import api from "./api";

const loginUser = async (data) => {
  try {
    const response = await api.post("/login", data);
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
