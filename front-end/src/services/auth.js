import api from "./api";

const loginUser = async (data) => {
  const login = JSON.stringify(data);
  const loginString = JSON.parse(login);
  const payloadString = loginString["payload"];
  console.log(payloadString);
  try {
    const response = await api.post("/login", payloadString);
    console.log(response);
    const token = response.data.token;
    const id = response.data.id;
    const type = response.data.tipoUsuario;
    saveTokenLocally(token);
    saveTypeLocally(type);
    saveIdLocally(id, type);

    return token;
  } catch (error) {
    console.error("Erro de login:", error);
    throw error;
  }
};

const saveTokenLocally = (token) => {
  localStorage.setItem("token", token);
};

const saveIdLocally = (id, type) => {
  if (type === "SURDO") {
    localStorage.setItem("idSurdo", id);
  } else if (type === "INTERPRETE") {
    localStorage.setItem("idInterprete", id);
  }
};

const saveTypeLocally = (type) => {
  localStorage.setItem("type", type);
};

const getToken = () => {
  return localStorage.getItem("token");
};

export { loginUser, getToken, saveTokenLocally };
