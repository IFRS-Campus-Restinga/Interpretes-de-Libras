import Header from "../../../components/header/header";
import "../login.css";
import { useState } from "react";
import { useForm } from "react-hook-form";

const RecuperaSenha = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");
  const handleLogin = () => {
    
  }
  return (
    <>
      <Header />
      <div className="login-container">
        <div className="login-group">
          <label>E-mail</label>
          <input
            className={errors?.email && "input-error"}
            value={email}
            type="text"
            placeholder="Escreva seu email"
            {...register("email", {
              onChange: (e) => {
                setEmail(e.target.value);
              },
              required: true,
            })}
          />
          {errors?.email?.type === "required" && (
            <p className="error-message">E-mail é um campo obrigatório.</p>
          )}
        </div>
        <div className="login-group">
          <label>CPF</label>
          <input
            className={errors?.cpf && "input-error"}
            value={cpf}
            type="text"
            placeholder="Escreva seu CPF"
            {...register("cpf", {
              onChange: (e) => {
                setCpf(e.target.value);
              },
              required: true,
            })}
          />
          {errors?.cpf?.type === "required" && (
            <p className="error-message">CPF é um campo obrigatório.</p>
          )}
        </div>
        <div className="login-group">
          <button className="loginButton" onClick={() => handleSubmit(handleLogin)()}>Recuperar senha</button>
        </div>
      </div>
    </>
  );
};

export default RecuperaSenha;
