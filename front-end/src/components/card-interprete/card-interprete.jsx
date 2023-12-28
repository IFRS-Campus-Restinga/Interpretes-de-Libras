import "./card-solicitacoes.css";

const CardInterprete = ({ nome, avaliacao }) => {
  const escolher = () => {
    console.log("implementar canditadura");
  };
  return (
    <div className="card">
      <div className="card-left">
        <div className="card-nome">Nome: {nome}</div>
        <div>Avaliacao: {avaliacao}</div>
      </div>
      <div className="card-rigth">
        <button class="card-button" onClick={() => escolher()}>
          Escolher
        </button>
      </div>
    </div>
  );
};

export default CardInterprete;
