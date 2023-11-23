import { useForm } from "react-hook-form";
import { isEmail } from "validator";
import { useState } from "react";
import "./formulario.css";
import { useDispatch } from "react-redux";
import { postSocilicitacaoCadastroSurdo } from "../../../store/fecthActions";

const FormularioSurdo = () => {
  const dispatch = useDispatch();
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const watchPassword = watch("senha");
  const [file, setFile] = useState(null);

  const onSubmit = (data) => {
    delete data.senhaConfirmation;
    delete data.file;

    const json = JSON.stringify(data);

    const formData = new FormData();
    formData.append("dados", json);
    formData.append("arquivo", file);

    console.log("data", formData.values);

    dispatch(postSocilicitacaoCadastroSurdo(formData));
  };

  return (
    <div className="form-container">
      <div className="form-container-line">
        <div className="form-group">
          <label>Nome</label>
          <input
            className={errors?.nome && "input-error"}
            type="text"
            placeholder="Escreva seu nome"
            {...register("nome", { required: true })}
          />
          {errors?.nome?.type === "required" && (
            <p className="error-message">Nome é um campo obrigatório.</p>
          )}
        </div>

        <div className="form-group">
          <label>Sobrenome</label>
          <input
            className={errors?.sobrenome && "input-error"}
            type="text"
            placeholder="Escreva seu sobrenome"
            {...register("sobrenome", { required: true })}
          />
          {errors?.sobrenome?.type === "required" && (
            <p className="error-message">Sobrenome é um campo obrigatório.</p>
          )}
        </div>
      </div>

      <div className="form-container-line">
        <div className="form-group">
          <label>CPF</label>
          <input
            className={errors?.cpf && "input-error"}
            type="text"
            placeholder="Escreva seu CPF"
            {...register("cpf", { required: true })}
          />
          {errors?.cpf?.type === "required" && (
            <p className="error-message">CPF é um campo obrigatório.</p>
          )}
        </div>

        <div className="form-group">
          <label>Data de Nascimento</label>
          <input
            className={errors?.dataNascimento && "input-error"}
            type="date"
            placeholder="Escreva sua Data de Nascimento"
            {...register("dataNascimento", { required: true })}
          />
          {errors?.dataNascimento?.type === "required" && (
            <p className="error-message">
              Data de Nascimento é um campo obrigatório.
            </p>
          )}
        </div>
      </div>

      <div className="form-container-line">
        <div className="form-group">
          <label>E-mail</label>
          <input
            className={errors?.email && "input-error"}
            type="email"
            placeholder="seuemail@mail.com"
            {...register("email", {
              required: true,
              validate: (value) => isEmail(value),
            })}
          />
          {errors?.email?.type === "required" && (
            <p className="error-message">Email é obrigatório.</p>
          )}

          {errors?.email?.type === "validate" && (
            <p className="error-message">Informe um email válido.</p>
          )}
        </div>

        <div className="form-group">
          <label>Telefone</label>
          <input
            className={errors?.telefone && "input-error"}
            type="text"
            placeholder="Escreva seu Telefone"
            {...register("telefone", { required: true })}
          />
          {errors?.telefone?.type === "required" && (
            <p className="error-message">Telefone é um campo obrigatório.</p>
          )}
        </div>
      </div>

      <div className="form-container-line">
        <div className="form-group">
          <label>Senha</label>
          <input
            className={errors?.senha && "input-error"}
            type="password"
            placeholder="Digite sua senha"
            {...register("senha", { required: true, minLength: 7 })}
          />

          {errors?.senha?.type === "required" && (
            <p className="error-message">Senha é um campo obrigatório.</p>
          )}

          {errors?.senha?.type === "minLength" && (
            <p className="error-message">
              A senha precisa ter no mínimo 7 caracteres.
            </p>
          )}
        </div>

        <div className="form-group">
          <label>Confirmação de Senha</label>
          <input
            className={errors?.senhaConfirmation && "input-error"}
            type="password"
            placeholder="Repita sua senha"
            {...register("senhaConfirmation", {
              required: true,
              validate: (value) => value === watchPassword,
            })}
          />
          {errors?.senhaConfirmation?.type === "required" && (
            <p className="error-message">
              Confirmação de senha é um campo obrigatório.
            </p>
          )}

          {errors?.senhaConfirmation?.type === "validate" && (
            <p className="error-message">As senhas não conferem.</p>
          )}
        </div>
      </div>

      <div className="form-container-line-one">
        <div className="form-group">
          <label>Documento</label>
          <input
            className={errors?.documento && "input-error"}
            type="file"
            {...register("file", {
              onChange: (e) => {
                setFile(e.target.files[0]);
              },
              required: true,
            })}
          />
          {errors?.documento?.type === "required" && (
            <p className="error-message">Documento é um campo obrigatório.</p>
          )}
        </div>
      </div>

      <div className="form-group">
        <button onClick={() => handleSubmit(onSubmit)()}>Criar conta</button>
      </div>
    </div>
  );
};

export default FormularioSurdo;
