import { Link } from "react-router-dom";
import "./header.css";

import logo from "../../images/logo.png";

const HeaderInterprete = () => {
  return (
    <div className="header">
      <Link className="app" to="/inteprete/perfil">
        Interpreto
        <img
          src={logo}
          alt="Imagem de mãos fazendo sinais"
          title="Logo no componente header"
        ></img>
      </Link>
      <div className="app-button">
        <div className="menu">
          <Link to="/inteprete/lista-solicitacoes-publicas">
            <button id="signUp">Solicitações Públicas</button>
          </Link>
        </div>
        <div className="menu">
          <Link to="/inteprete/lista-solicitacoes-aprovadas">
            <button id="signUp">Solicitações Aprovadas</button>
          </Link>
        </div>
        <div className="menu">
          <Link to="/">
            <button id="signUp">Sair</button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default HeaderInterprete;
