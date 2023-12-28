import "./card-solicitacoes.css";

const CardSolicitacaoAprovada = ({ surdoNome, dataEncontro, hora, local }) => {
  return (
    <div className="card">
      <div className="card-left">
        <div className="card-nome">Nome: {surdoNome}</div>
        <div>Data Encontro: {dataEncontro}</div>
        <div>Horário Encontro: {hora} </div>
        <div>Endereço Encontro: {local} </div>
      </div>
    </div>
  );
};

export default CardSolicitacaoAprovada;
