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
      .get("/solicitacao")
      .then((response) => {
        console.log(response.data);
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
        alert("Cadastro realizado com sucesso!");
      })
      .catch((err) => {
        console.log(err.response.data);
        alert(err.response.data);
      });
  };
};

export const postCandidaturaSolicitacaoInterprete = (candidatura) => {
  return (dispatch) => {
    api
      .post("/solicitacao", candidatura)
      .then((response) => {
        console.log(response.data);
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

export const getDadosFormSurdos = (cpf) => {
  return () => {
    api.get("/surdo").then((response) => {
      const surdos = response.data;
      surdos.forEach((element) => {
        if (element.cpf === cpf) {
          console.log(element);
        }
      });
    });
  };
};

export const getDadosFormSurdo = (id) => {
  return (dispatch) => {
    api.get(`/surdo/${id}`).then((response) => {
      console.log("surdo", response.data);
    });
  };
};

export const putDadosFormSurdo = (id, surdo) => {
  return (dispatch) => {
    console.log(surdo);
    api
      .put(`/surdo/${id}`, surdo)
      .then((response) => {
        if (response.data.id != null) {
          alert("Perfil do usuário atualizado com sucesso!");
        } else {
          alert("Erro ao atualizar perfil!");
        }
        console.log(response);
      })
      .catch((err) => {
        console.log(err);
      });
  };
};

export const putEditarPerfilInterprete = (id, interprete) => {
  return (dispatch) => {
    api
      .put(`/interprete/${id}`, interprete)
      .then((response) => {
        if (response.data.id != null) {
          alert("Perfil do usuário atualizado com sucesso!");
        } else {
          alert("Erro ao atualizar perfil!");
        }
        console.log(response);
      })
      .catch((err) => {
        console.log(err);
      });
  };
};

export const postCadastroSolicitacaoInterprete = (data) => {
  return () => {
    api.post("/solicitacao", data).then((response) => {
      console.log("solicitacao", response);
    });
  };
};
