import CardSolicitacaoPublica from "../../components/solicitacoes-publicas/solicitacoes-publicas";
import { useDispatch } from "react-redux";
import { useEffect, useState } from "react";
import api from "../../services/api";

const ListaSolicitacoesPublicas = () => {
  const dispatch = useDispatch();
  const [solicitacoes, setSolicitacoes] = useState();

  useEffect(() => {
    api.get("/solicitacao").then((response) => {
      if (response?.data) {
        setSolicitacoes(response.data);
      }
    });
  }, [dispatch]);

  return (
    <div>
      {solicitacoes?.map((solicitacao, index) => {
        return (
          <CardSolicitacaoPublica
            key={index}
            dataEncontro={solicitacao.dataEncontro}
            hora={solicitacao.horaEncontro}
            local={solicitacao.endereco}
            idSolicitacao={solicitacao.solicitacaoId}
            statusSolicitacao={solicitacao.statusSolicitacao}
          />
        );
      })}
    </div>
  );
};

export default ListaSolicitacoesPublicas;
