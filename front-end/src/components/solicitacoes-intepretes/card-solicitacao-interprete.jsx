import "./card-solicitacoes.css";
import { Link } from "react-router-dom";

const CardSolicitacaoInterprete = ({ dataEncontro, hora, local }) => {
  const cancelar = () => {
    console.log("implementar cancelamento");
  };
  return (
    <div className="card">
      <div className="card-left">
        <div>Data Encontro: {dataEncontro}</div>
        <div>Horário Encontro: {hora} </div>
        <div>Endereço Encontro: {local} </div>
      </div>
      <div className="card-rigth">
        <button class="card-button" onClick={() => cancelar()}>
          Cancelar
        </button>
        <Link to="/candidatura-solicitacao">
          <button class="card-button">Ver candidaturas</button>
        </Link>
      </div>
    </div>
  );
};

export default CardSolicitacaoInterprete;
