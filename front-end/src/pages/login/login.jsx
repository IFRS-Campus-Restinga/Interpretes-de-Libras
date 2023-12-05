import { useState } from "react";
import { loginUser } from "../../services/auth";
import { useForm } from "react-hook-form";
import { useDispatch } from "react-redux";
import Header from "../../components/header/header";
import "./login.css";
import { useNavigate, Link } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const handleLogin = async () => {
    try {
      const payload = {
        email: username,
        senha: password,
      };

      await loginUser({ payload }, dispatch);

      if (localStorage.getItem("tipoUsuario") === "SURDO") {
        navigate("/surdo/perfil");
      } else if (localStorage.getItem("tipoUsuario") === "INTERPRETE") {
        navigate("/inteprete/perfil");
      } else if (localStorage.getItem("tipoUsuario") === "ADMIN") {
        navigate("/admin");
      }
    } catch (error) {
      //TODO:modal alerta
      alert("E-mail ou senha inválidos!");
      console.error("Erro durante o login:", error);
    }
  };

  return (
    <>
      <Header />
      <div className="login-container">
        <div className="login-group">
          <label>E-mail</label>
          <input
            className={errors?.email && "input-error"}
            onChange={(e) => setUsername(e.target.value)}
            value={username}
            type="text"
            placeholder="Escreva seu e-mail"
            {...register("email", {
              onChange: (e) => {
                setUsername(e.target.value);
              },
              required: true,
            })}
          />
          {errors?.email?.type === "required" && (
            <p className="error-message">E-mail é um campo obrigatório.</p>
          )}
        </div>
        <div className="login-group">
          <label>Senha</label>
          <input
            className={errors?.senha && "input-error"}
            value={password}
            type="password"
            placeholder="Escreva sua senha"
            {...register("senha", {
              onChange: (e) => {
                setPassword(e.target.value);
              },
              required: true,
            })}
          />
          {errors?.senha?.type === "required" && (
            <p className="error-message">Senha é um campo obrigatório.</p>
          )}
        </div>
        <div className="login-group">
          <Link className="forgotPassword" to="/login/forgotPassword">
            <p>
              Esqueceu a sua senha? Clique aqui!
            </p>
          </Link>
        </div>
        <div className="login-group">
          <button
            onClick={() => handleSubmit(handleLogin)()}
            className="loginButton"
          >
            Login
          </button>
        </div>
      </div>
    </>
  );
};

export default Login;
