import "./login.css";

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
        <button>Login</button>
      </div>
    </div>
  );
};

export default Login;
