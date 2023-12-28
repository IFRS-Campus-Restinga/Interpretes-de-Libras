import CardSolicitacaoAprovada from "../../components/solicitacoes-aprovadas/solicitacoes-aprovadas";
import { useDispatch } from "react-redux";
import { useEffect } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";

const ListaSolicitacoesAprovadas = ({ solicitacoes }) => {
  const dispatch = useDispatch();

  useEffect(() => {
    //dispatch(getAllSocilicitacoesPublicas());
  }, [dispatch]);

  return (
    <div>
      {solicitacoes?.map((solicitacao, index) => {
        return (
          <CardSolicitacaoAprovada
            key={index}
            surdoNome={solicitacao.surdoNome}
            dataEncontro={solicitacao.dataEncontro}
            hora={solicitacao.hora}
            local={solicitacao.local}
          />
        );
      })}
    </div>
  );
};

ListaSolicitacoesAprovadas.propTypes = {
  solicitacoes: PropTypes.array.isRequired,
};

const mapStateToProps = (state) => ({
  solicitacoes: state.solicitacoesPublicas,
});

export default connect(mapStateToProps)(ListaSolicitacoesAprovadas);
