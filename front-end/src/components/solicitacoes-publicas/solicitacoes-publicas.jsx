import "./card-solicitacoes.css";

const CardSolicitacaoPublica = ({ surdoNome, dataEncontro, hora, local }) => {
  const candidatar = () => {
    console.log("implementar canditadura");
  };
  return (
    <div className="card">
      <div className="card-left">
        <div className="card-nome">Nome: {surdoNome}</div>
        <div>Data Encontro: {dataEncontro}</div>
        <div>Horário Encontro: {hora} </div>
        <div>Endereço Encontro: {local} </div>
      </div>
      <div className="card-rigth">
        <button class="card-button" onClick={() => candidatar()}>
          Candidatar-se
        </button>
      </div>
    </div>
  );
};

export default CardSolicitacaoPublica;
