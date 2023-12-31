import { Link } from "react-router-dom";
import "./header.css";

import logo from "../../images/logo.png";

const Header = () => {
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
        <Link to="/login">
          <button id="login">Login</button>
        </Link>
        <Link to="/cadastro">
          <button id="signUp">Cadastrar-se</button>
        </Link>
      </div>
    </div>
  );
};

export default Header;
