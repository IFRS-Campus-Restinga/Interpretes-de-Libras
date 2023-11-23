import CardSolicitacaoPublica from "../../components/solicitacoes-publicas/solicitacoes-publicas";
import { useDispatch } from "react-redux";
import { useEffect } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";

const ListaSolicitacoesPublicas = ({ solicitacoes }) => {
  const dispatch = useDispatch();

  useEffect(() => {
    //dispatch(getAllSocilicitacoesPublicas());
  }, [dispatch]);

  return (
    <div>
      {solicitacoes?.map((solicitacao, index) => {
        return (
          <CardSolicitacaoPublica
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

ListaSolicitacoesPublicas.propTypes = {
  solicitacoes: PropTypes.array.isRequired,
};

const mapStateToProps = (state) => ({
  solicitacoes: state.solicitacoesPublicas,
});

export default connect(mapStateToProps)(ListaSolicitacoesPublicas);
