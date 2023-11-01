import "./login.css";
import { Link } from "react-router-dom";

const Login = () => {
  return (
    <div className="login-container">
      <div className="login-group">
        <label>E-mail</label>
        <input type="text" placeholder="Escreva seu e-mail" />
      </div>
      <div className="login-group">
        <label>Senha</label>
        <input type="text" placeholder="Escreva seu e-mail" />
      </div>
      <div className="login-group">
        <Link to="/perfil">
          <button className="loginButton">Login</button>
        </Link>  
      </div>
    </div>
  );
};

export default Login;
