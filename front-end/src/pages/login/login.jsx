import { useState } from "react";
import { loginUser } from "../../services/auth";
import { useForm } from "react-hook-form";
import "./login.css";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const handleLogin = async () => {
    try {
      const payload = {
        email: username,
        senha: password,
      };
      const token = await loginUser({ payload });
      console.log("Token obtido:", token);
      if (getUserType() === "SURDO") {
        window.location.href = "/perfil/surdo";
      } else if (getUserType() === "INTERPRETE") {
        window.location.href = "/perfil/interprete";
      }
    } catch (error) {
      alert("E-mail ou senha inválidos!");
      console.error("Erro durante o login:", error);
    }
  };

  const getUserType = () => {
    return localStorage.getItem("type");
  };

  return (
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
        <button
          onClick={() => handleSubmit(handleLogin)()}
          className="loginButton"
        >
          Login
        </button>
      </div>
    </div>
  );
};

export default Login;
