import CardSolicitacaoInterprete from "../../components/solicitacoes-intepretes/card-solicitacao-interprete";
import { useDispatch } from "react-redux";
import { useEffect } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";

const ListaSolicitacoesIntepretes = ({ solicitacoes }) => {
  const dispatch = useDispatch();

  useEffect(() => {
    //dispatch(getAllSocilicitacoesPublicas());
  }, [dispatch]);

  return (
    <div>
      {solicitacoes?.map((solicitacao, index) => {
        return (
          <CardSolicitacaoInterprete
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

ListaSolicitacoesIntepretes.propTypes = {
  solicitacoes: PropTypes.array.isRequired,
};

const mapStateToProps = (state) => ({
  solicitacoes: state.socicitacoesInterpretes,
});

export default connect(mapStateToProps)(ListaSolicitacoesIntepretes);
