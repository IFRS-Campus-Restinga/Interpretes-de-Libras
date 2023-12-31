import "./card-solicitacoes.css";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import {
  putAvaliacaoUsuario,
  aprovarCadastroPut,
} from "../../store/fecthActions";
import classNames from "classnames";
import api from "../../services/api";

const CardSolicitacao = ({
  id,
  dataCriacao,
  nome,
  telefone,
  email,
  status,
  tipoUsuario,
}) => {
  const [stateSolicitacao, setStateSolicitacao] = useState(status);
  const [msg, setMsg] = useState("");
  const [statusAvaliacao, setStatusAvaliacao] = useState(status);
  const dispatch = useDispatch();
  const classStatus = classNames("card-status", statusAvaliacao);

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
    dispatch(putAvaliacaoUsuario(id, statusAvaliacao));
    localStorage.setItem("statusAvaliacao", statusAvaliacao);
  };

  const reprovarCadastro = () => {
    setMsg("não deve estar em branco");
    setStatusAvaliacao("INDEFERIDO");
    setStateSolicitacao("INDEFERIDO");
    dispatch(putAvaliacaoUsuario(id, statusAvaliacao));
    localStorage.setItem("statusAvaliacao", statusAvaliacao);
  };

  const getAvaliacaoCadastro = () => {
    return localStorage.getItem("statusAvaliacao");
  };

  const handleDownloadButtonClick = () => {
    return (dispatch) => {
      api
        .get(`/avaliacaousuario/download/avaliacaoId/${id}`, {
          responseType: "blob",
        })
        .then((response) => {
          const file = new Blob([response.data], { type: "application/pdf" });
          const fileURL = URL.createObjectURL(file);
          window.open(fileURL);
        })
        .catch(console.log);
    };
  };

  return (
    <div className="card">
      <div className="card-left">
        <div className="card-nome">{nome}</div>
        <div>Data criacao: {dataCriacao}</div>
        <div>Telefone: {telefone} </div>
        <div>E-mail: {email} </div>
        <div>Tipo de usuário: {tipoUsuario} </div>
        <div>
          Documento:
          <span className="downloadFile" onClick={handleDownloadButtonClick()}>
            {" "}
            Download do documento
          </span>
        </div>
      </div>
      <div className="card-rigth">
        <div className={classStatus}>{statusAvaliacao}</div>
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
