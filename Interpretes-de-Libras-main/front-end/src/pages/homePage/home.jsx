import CardSolicitacao from "../../components/solicitacoes/card-solicitacoes";
import { useSelector, useDispatch } from "react-redux";
import { useEffect } from "react";

import { getAllSocilicitacoesCadastro } from "../../store/fecthActions";

const Home = () => {
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
            nome={solicitacao.nome}
            endereco={solicitacao.endereco}
            telefone={solicitacao.telefone}
            email={solicitacao.email}
            status={solicitacao?.state ? solicitacao.state : "ANALISE"}
          />
        );
      })}
    </div>
  );
};

export default Home;
