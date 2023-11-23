import "./card-solicitacoes.css";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import {
  putSocilicitacaoCadastro,
  aprovarCadastroPut,
} from "../../store/fecthActions";
import classNames from "classnames";

const CardSolicitacao = ({
  id,
  dataCriacao,
  nome,
  telefone,
  email,
  status,
}) => {
  const [stateSolicitacao, setStateSolicitacao] = useState(status);
  const [msg, setMsg] = useState("");
  const [statusAvaliacao, setStatusAvaliacao] = useState("");
  const dispatch = useDispatch();
  const classStatus = classNames("card-status", status);

  useEffect(() => {
    console.log(stateSolicitacao);
    dispatch(
      aprovarCadastroPut({ msg: msg, statusAvaliacao: statusAvaliacao }, id)
    );
  }, [stateSolicitacao, dispatch]);

  const aprovarCadastro = () => {
    setMsg("não deve estar em branco");
    setStatusAvaliacao("DEFERIDO");
    setStateSolicitacao("DEFERIDO");
  };

  const reprovarCadastro = () => {
    setMsg("não deve estar em branco");
    setStatusAvaliacao("INDEFERIDO");
    setStateSolicitacao("INDEFERIDO");
  };

  return (
    <div className="card">
      <div className="card-left">
        <div className="card-nome">{nome}</div>
        <div>Data criacao: {dataCriacao}</div>
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
