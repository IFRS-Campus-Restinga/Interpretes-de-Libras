import Header from "../../../components/header/header";
import "../login.css";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { useDispatch } from "react-redux";
import api from "../../../services/api";

const ForgotPassword = () => {
  const dispatch = useDispatch();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const handleLogin = () => {
    dispatch(postAtualizaSenha());
  };
  const postAtualizaSenha = (dispacth) => {
    return (dispatch) => {
      const payload = {
        email: email,
        cpf: cpf,
      };
      api
        .post("/usuario/recuperarsenha", payload)
        .then((response) => {
          console.log(response.data);
          dispatch(putAtualizaSenha(response.data.id));
        })
        .catch((err) => {
          console.log(err.response.data);
          alert(err.response.data);
        });
    };
  };
  const putAtualizaSenha = (id) => {
    return (dispatch) => {
      const payload = {
        id: id,
        senha: newPassword,
      };
      api
        .put("/usuario/recuperarsenha", payload)
        .then((response) => {
          console.log(response.data);
          alert(response.data);
        })
        .catch((err) => {
          console.log(err.response.data);
          alert(err.response.data);
        });
    };
  };
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
          <label>Nova senha</label>
          <input
            className={errors?.newPassword && "input-error"}
            value={newPassword}
            type="password"
            placeholder="Escreva a sua nova senha"
            {...register("newPassword", {
              onChange: (e) => {
                setNewPassword(e.target.value);
              },
              required: true,
            })}
          />
          {errors?.newPassword?.type === "required" && (
            <p className="error-message">Nova senha é um campo obrigatório.</p>
          )}
        </div>
        <div className="login-group">
          <button
            className="loginButton"
            onClick={() => handleSubmit(handleLogin)()}
          >
            Recuperar senha
          </button>
        </div>
      </div>
    </>
  );
};

export default ForgotPassword;
