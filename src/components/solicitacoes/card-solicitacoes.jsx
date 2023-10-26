import "./card-solicitacoes.css";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { putSocilicitacaoCadastro } from "../../store/fecthActions";
import classNames from "classnames";

const CardSolicitacao = ({ nome, endereco, telefone, email, status }) => {
  const [stateSolicitacao, setStateSolicitacao] = useState(status);
  const dispatch = useDispatch();
  const classStatus = classNames("card-status", status);

  useEffect(() => {
    console.log(stateSolicitacao);
    dispatch(putSocilicitacaoCadastro(stateSolicitacao));
  }, [stateSolicitacao, dispatch]);

  const aprovarCadastro = () => {
    setStateSolicitacao("APROVADO");
  };

  const reprovarCadastro = () => {
    setStateSolicitacao("REPROVADO");
  };

  return (
    <div className="card">
      <div className="card-left">
        <div className="card-nome">{nome}</div>
        <div>Endereço: {endereco} </div>
        <div>Telefone: {telefone} </div>
        <div>E-mail: {email} </div>
      </div>
      <div className="card-rigth">
        <div className={classStatus}>{stateSolicitacao}</div>
        <div class="card-container-button">
          <button class="card-button" onClick={() => aprovarCadastro()}>
            Aprovar Cadastro
          </button>
          <button class="card-button" onClick={() => reprovarCadastro()}>
            Reprovar Cadastro
          </button>
        </div>
      </div>
    </div>
  );
};

export default CardSolicitacao;
