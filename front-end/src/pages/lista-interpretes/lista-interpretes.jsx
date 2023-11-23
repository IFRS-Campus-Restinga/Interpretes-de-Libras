import { useDispatch } from "react-redux";
import { useEffect } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import CardInterprete from "../../components/card-interprete/card-interprete";

const ListaInterpretes = ({ interpretes }) => {
  const dispatch = useDispatch();

  useEffect(() => {
    //dispatch(getAllSocilicitacoesPublicas());
  }, [dispatch]);

  return (
    <div>
      {interpretes?.map((interprete, index) => {
        return (
          <CardInterprete
            key={index}
            nome={interprete.nome}
            avaliacao={interprete.avaliacao}
          />
        );
      })}
    </div>
  );
};

ListaInterpretes.propTypes = {
  interpretes: PropTypes.array.isRequired,
};

const mapStateToProps = (state) => ({
  interpretes: state.socicitacoesInterpretes[0].interpretes,
});

export default connect(mapStateToProps)(ListaInterpretes);
