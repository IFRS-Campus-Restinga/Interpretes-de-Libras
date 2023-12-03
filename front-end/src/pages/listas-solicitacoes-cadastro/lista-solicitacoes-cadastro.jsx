import CardSolicitacao from "../../components/solicitacoes-cadastro/card-solicitacoes";
import { useSelector, useDispatch } from "react-redux";
import { useEffect, useState } from "react";
import api from "../../services/api";

const ListaSolicitacoesDeCadastro = () => {
  const solicitacoes = useSelector((state) => state.solicitacoes);
  const [avaliacaousuario, setAvaliacaoUsuario] = useState([]);
  const dispatch = useDispatch();

  const getAllSocilicitacoesCadastro = () => {
    return (dispatch) => {
      const token = localStorage.getItem("token");
      if (token) {
        api.defaults.headers.common["Authorization"] = `Bearer ${token}`;
      }

      api
        .get("/avaliacaousuario")
        .then((response) => {
          console.log(response.data);
          setAvaliacaoUsuario(Object.values(response.data));
        })
        .catch(console.log);
    };
  };

  useEffect(() => {
    dispatch(getAllSocilicitacoesCadastro());
  }, [dispatch]);

  return (
    <div>
      {avaliacaousuario.map((solicitacao, index) => {
        return (
          <CardSolicitacao
            key={index}
            id={solicitacao.id}
            dataCriacao={solicitacao.dataCriacao}
            nome={solicitacao.nomeUsuario}
            telefone={solicitacao.telefoneUsuario}
            email={solicitacao.emailUsuario}
            status={solicitacao.statusAvaliacao}
          />
        );
      })}
    </div>
  );
};

export default ListaSolicitacoesDeCadastro;
