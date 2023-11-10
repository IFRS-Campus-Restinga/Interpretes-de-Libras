import CardSolicitacao from "../../components/solicitacoes/card-solicitacoes";
import { useSelector, useDispatch } from "react-redux";
import { useEffect } from "react";

import { getAllSocilicitacoesCadastro } from "../../store/fecthActions";

const ListaSolicitacoes = () => {
  const solicitacoes = useSelector((state) => state.solicitacoes);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getAllSocilicitacoesCadastro());
  }, [dispatch]);

  return (
    <div>
      {solicitacoes.map((solicitacao, index) => {
        return (
          <CardSolicitacao
            key={index}
            id={solicitacao.id}
            dataCriacao={solicitacao.dataCriacao}
            nome={solicitacao.nome}
            telefone={solicitacao.telefone}
            email={solicitacao.email}
            status={solicitacao.statusAvaliacao}
          />
        );
      })}
    </div>
  );
};

export default ListaSolicitacoes;
