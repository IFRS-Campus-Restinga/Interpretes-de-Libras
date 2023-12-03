import CardSolicitacao from "../../components/solicitacoes-cadastro/card-solicitacoes";
import { useSelector, useDispatch } from "react-redux";
<<<<<<< HEAD
import { useEffect } from "react";
import { getAllSocilicitacoesCadastro } from "../../store/fecthActions/index";
=======
import { useEffect, useState } from "react";
import api from "../../services/api";
>>>>>>> 3ddd511d640f9a1d45e7643f86525bdb198aa557

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
<<<<<<< HEAD
      {Array.from(Array(5), (element, index) => {
        return (
          <CardSolicitacao
            key={"0"}
            id={"0"}
            dataCriacao={"1998"}
            nome={"122313"}
            telefone={"455"}
            email={"123"}
            status={"231"}
=======
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
>>>>>>> 3ddd511d640f9a1d45e7643f86525bdb198aa557
          />
        );
      })}
    </div>
  );
};

export default ListaSolicitacoesDeCadastro;
