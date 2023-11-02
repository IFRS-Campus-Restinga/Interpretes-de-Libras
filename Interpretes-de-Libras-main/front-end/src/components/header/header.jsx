import { Link } from "react-router-dom";
import "./header.css";

const Header = () => {
  return (
    <div className="header">
      <Link className="app" to="/">
        APP NAME
      </Link>
      <div className="menu">
        <Link className="app" to="/login">
          LOGIN
        </Link>
        <Link className="app" to="/cadastro">
          CADASTRO
        </Link>
      </div>
    </div>
  );
};

export default Header;
