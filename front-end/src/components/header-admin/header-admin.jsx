import { Link } from "react-router-dom";
import "./header.css";

import logo from "../../images/logo.png";

const HeaderAdmin = () => {
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
      <div className="menu">
        <Link to="/lista-solicitacoes-cadastro">
          <button id="signUp">Solicitações de Casdastro</button>
        </Link>
      </div>
    </div>
  );
};

export default HeaderAdmin;
