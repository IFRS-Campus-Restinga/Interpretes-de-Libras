import { Link } from "react-router-dom";
import "./header.css";

import logo from "../../images/logo.png";

const HeaderSurdo = () => {
  return (
    <div className="header">
      <Link className="app" to="/">
        Interpreto
        <img
          src={logo}
          alt="Imagem de mãos fazendo sinais"
          title="Logo no componente header"
        ></img>
      </Link>
      <div className="app-button">
        <div className="menu">
          <Link to="/cadastrar-solicitacao-interprete">
            <button id="signUp">Cadastrar Solicitação</button>
          </Link>
        </div>
        <div className="menu">
          <Link to="/minhas-solicitacoes">
            <button id="signUp">Minhas Solicitações</button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default HeaderSurdo;
