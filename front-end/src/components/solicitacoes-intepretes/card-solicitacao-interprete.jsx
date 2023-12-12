import "./card-solicitacoes.css";
import { Link } from "react-router-dom";
import classNames from "classnames";

const CardSolicitacaoInterprete = ({ dataEncontro, hora, local, status }) => {
  const enderecoString =
    `Rua: ${local?.rua}, ` +
    `${local?.numero}. ` +
    `Complemento: ${local?.complemento}. ` +
    `Cidade: ${local?.cidade}, ` +
    `Bairro: ${local?.bairro}, ` +
    `Observação: ${local?.observacaoEndereco}, ` +
    `Ponto de Referência: ${local?.pontoReferencia}`;

  const classStatus = classNames("card-status", status);

  const cancelar = () => {
    console.log("implementar cancelamento");
  };

  return (
    <div className="card">
      <div className="card-left">
        <div>Data Encontro: {dataEncontro}</div>
        <div>Horário Encontro: {hora} </div>
        <div>Endereço Encontro: {enderecoString} </div>
      </div>
      <div className="card-rigth">
        <div className={classStatus}>{status}</div>
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
