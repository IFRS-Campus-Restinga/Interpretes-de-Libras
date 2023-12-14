import CardSolicitacaoInterprete from "../../components/solicitacoes-intepretes/card-solicitacao-interprete";
import { useDispatch } from "react-redux";
import { useEffect, useState } from "react";
import api from "../../services/api";

const ListaSolicitacoesIntepretes = () => {
  const dispatch = useDispatch();
  const [solicitacoes, setSolicitacoes] = useState();

  const id = localStorage.getItem("id");
  console.log("ListaSolicitacoesIntepretes", id);

  useEffect(() => {
    api.get(`/surdo/MinhasSolicitacoes/${id}`).then((response) => {
      if (response?.data) {
        setSolicitacoes(response.data);
      }
    });
  }, [dispatch]);

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
            id={solicitacao.solicitacaoId}
          />
        );
      })}
    </div>
  );
};

export default ListaSolicitacoesIntepretes;
