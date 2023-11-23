import { useState } from "react";
import { loginUser } from "../../services/auth";
import { Link } from "react-router-dom";
import "./login.css";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const token = await loginUser({ username, password });
      console.log("Token obtido:", token);
    } catch (error) {
      console.error("Erro durante o login:", error);
    }
  };

  return (
    <div className="login-container">
      <div className="login-group">
        <label>E-mail</label>
        <input
          onChange={(e) => setUsername(e.target.value)}
          value={username}
          type="text"
          placeholder="Escreva seu e-mail"
        />
      </div>
      <div className="login-group">
        <label>Senha</label>
        <input
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          type="text"
          placeholder="Escreva seu e-mail"
        />
      </div>
      <div className="login-group">
        <Link to="/perfil">
          <button onClick={handleLogin} className="loginButton">
            Login
          </button>
        </Link>
      </div>
    </div>
  );
};

export default Login;
