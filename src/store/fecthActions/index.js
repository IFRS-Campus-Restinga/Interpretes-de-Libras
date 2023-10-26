import api from "../../services/api";
import {
  getAllSolicitacoesCadastros,
  addSolicitacaoCadastro,
  changeStatusSolicitacaoCadastro,
} from "../solicitacoes-cadastro";

export const getAllSocilicitacoesCadastro = () => {
  return (dispatch) => {
    api
      .get("/solicitacoesCadastro")
      .then((response) => {
        dispatch(getAllSolicitacoesCadastros(response.data));
      })
      .catch(console.log);
  };
};

export const postSocilicitacaoCadastro = (solcitacao) => {
  return (dispatch) => {
    api
      .post("/addSolicitacaoCadastro", solcitacao)
      .then((response) => {
        dispatch(addSolicitacaoCadastro(response.data));
      })
      .catch(console.log);
  };
};

export const putSocilicitacaoCadastro = (solcitacao) => {
  return (dispatch) => {
    api
      .post("/changeSolicitacaoCadastro", solcitacao)
      .then((response) => {
        dispatch(changeStatusSolicitacaoCadastro(response.data));
      })
      .catch(console.log);
  };
};
