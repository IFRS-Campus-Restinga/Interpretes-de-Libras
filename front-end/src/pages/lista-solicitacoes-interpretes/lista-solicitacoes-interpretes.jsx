import CardSolicitacaoInterprete from "../../components/solicitacoes-intepretes/card-solicitacao-interprete";
import { useDispatch } from "react-redux";
import { useEffect } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getSolicitacoesInterprete } from "../../store/fecthActions";

const ListaSolicitacoesIntepretes = ({ solicitacoes, id }) => {
  const dispatch = useDispatch();
  console.log("ListaSolicitacoesIntepretes", id);
  useEffect(() => {
    dispatch(getSolicitacoesInterprete());
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
  id: PropTypes.string.isRequired,
};

const mapStateToProps = (state) => ({
  solicitacoes: state.socicitacoesInterpretes,
  id: state.perfil[0].id,
});

export default connect(mapStateToProps)(ListaSolicitacoesIntepretes);
