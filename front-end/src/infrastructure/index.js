import axios from "axios";
//Não é utilizado segundo pessoal do front-end
const connection = axios.create({
  baseURL: "http://localhost:8080",
});

const GET = "GET";
const POST = "POST";
const PUT = "PUT";
const DELETE = "DELETE";

const request = (path, method, data = {}) => {
  return new Promise((resolve, reject) => {
    connection
      .request({ url: path, method, data: { payload: data } })
      .then((response) => {
        resolve(response.data.payload);
      })
      .catch((e) => {
        if (e.response !== undefined && e.response !== "") {
          reject(e.response.data.errors);
        } else {
          const error = [];
          reject(error);
        }
      });
  });
};

const api = {};

api.enviarDadosFormulario = (data) => {
  return request("/cadastrar", POST, data);
};

export default api;
