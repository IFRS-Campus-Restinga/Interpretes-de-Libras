import CardSolicitacaoInterprete from "../../components/solicitacoes-intepretes/card-solicitacao-interprete";
import { useDispatch } from "react-redux";
import { useEffect, useState } from "react";
import api from "../../services/api";

const ListaSolicitacoesIntepretes = () => {
  const dispatch = useDispatch();
  const [solicitacoes, setSolicitacoes] = useState([]);
  const [interpretes, setInterpretes] = useState([]);

  const id = localStorage.getItem("id");

  let listSolicitacao = [];
  let listInterpretes = [];

  useEffect(() => {
    api.get(`/surdo/MinhasSolicitacoes/${id}`).then((response) => {
      debugger;
      setSolicitacoes();
      if (response?.data) {
        response.data.solicitacoesComInterpretes.map((item) => {
          listSolicitacao.push(item.solicitacao);
          listInterpretes.push(item.interpretes);
        });
      }
      setSolicitacoes(listSolicitacao);
      setInterpretes(listInterpretes);
    });
  }, [dispatch, id]);

  return (
    <div>
      {solicitacoes?.map((solicitacao, index) => {
        return (
          <CardSolicitacaoInterprete
            key={index}
            surdoNome={solicitacao.surdoNome}
            dataEncontro={solicitacao.dataEncontro}
            hora={solicitacao.horaEncontro}
            local={solicitacao.endereco}
            status={solicitacao.statusSolicitacao}
            id={solicitacao.id}
          />
        );
      })}
    </div>
  );
};

export default ListaSolicitacoesIntepretes;
