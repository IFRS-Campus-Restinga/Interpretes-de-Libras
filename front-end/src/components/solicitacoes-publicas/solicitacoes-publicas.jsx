import "./card-solicitacoes.css";
import api from "../../services/api";
import classNames from "classnames";

const CardSolicitacaoPublica = ({
  dataEncontro,
  hora,
  local,
  idSolicitacao,
  statusSolicitacao,
}) => {
  const classStatus = classNames("card-status", statusSolicitacao);
  const enderecoString =
    `Rua: ${local?.rua}, ` +
    `${local?.numero}. ` +
    `Complemento: ${local?.complemento}. ` +
    `Cidade: ${local?.cidade}, ` +
    `Bairro: ${local?.bairro}, ` +
    `Observação: ${local?.observacaoEndereco}, ` +
    `Ponto de Referência: ${local?.pontoReferencia}`;

  const candidatar = () => {
    const interpreteId = localStorage.getItem("id");

    api
      .post(
        `/solicitacao/selecionarCandidatura/aceitar?solicitacaoId=${idSolicitacao}&interpreteId=${interpreteId}`
      )
      .then((response) => {
        if (response?.data) {
          window.location.reload();
        }
      });
  };

  return (
    <div className="card">
      <div className="card-left">
        <div>Data Encontro: {dataEncontro}</div>
        <div>Horário Encontro: {hora} </div>
        <div>Endereço Encontro: {enderecoString} </div>
      </div>
      <div className="card-rigth">
        <div className={classStatus}>{statusSolicitacao}</div>
        {statusSolicitacao !== "CANCELADA" &&
          statusSolicitacao !== "ACEITA" && (
            <button class="card-button" onClick={() => candidatar()}>
              Candidatar-se
            </button>
          )}
      </div>
    </div>
  );
};

export default CardSolicitacaoPublica;
