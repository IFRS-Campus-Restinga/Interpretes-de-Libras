import api from "../../services/api";
import {
  getAllSolicitacoesCadastros,
  addSolicitacaoCadastro,
  changeStatusSolicitacaoCadastro,
} from "../solicitacoes-cadastro";

// export const getAllSocilicitacoesCadastro = () => {
//   return (dispatch) => {
//     api
//       .get("/avaliacaousuario")
//       .then((response) => {
//         dispatch(getAllSolicitacoesCadastros(response.data));
//       })
//       .catch(console.log);
//   };
// };

export const getAllSocilicitacoesCadastro = () => {
  return (dispatch) => {
    const token = localStorage.getItem("token");
    if (token) {
      api.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    }

    api
      .get("/avaliacaousuario")
      .then((response) => {
        dispatch(getAllSolicitacoesCadastros(response.data));
      })
      .catch(console.log);
  };
};

export const postSocilicitacaoCadastroSurdo = (solcitacao) => {
  return (dispatch) => {
    api
      .post("/surdo", solcitacao)
      .then((response) => {
        dispatch(addSolicitacaoCadastro(response.data));
      })
      .catch(console.log);
  };
};

/*export const putSocilicitacaoCadastro = (solcitacao) => {
  return (dispatch) => {
    api
      .post("/changeSolicitacaoCadastro", solcitacao)
      .then((response) => {
        dispatch(changeStatusSolicitacaoCadastro(response.data));
      })
      .catch(console.log);
  };
};*/

export const aprovarCadastroPut = (data, id) => {
  console.log(data);
  return () => {
    api
      .put(`/avaliacaousuario/${id}`, data)
      .then((response) => {
        console.log("avaliacaousuario", response);
      })
      .catch(console.log);
  };
};

export const getDadosFormInterprete = (id) => {
  return () => {
    api.get(`/interprete/${id}`).then((response) => {
      console.log("interprete", response);
    });
  };
};

export const postCadastroSolicitacaoInterprete = (data) => {
  return () => {
    api.get("/solicitacao", data).then((response) => {
      console.log("solicitacao", response);
    });
  };
};
