import { useForm } from "react-hook-form";
import { isEmail } from "validator";
import { useEffect, useState } from "react";
import api from "../../../services/api";
import "./formulario.css";
import { useDispatch } from "react-redux";
import { putDadosFormSurdo } from "../../../store/fecthActions";

const FormularioEditarSurdo = () => {
  const dispatch = useDispatch();
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const watchPassword = watch("senha");
  const [file, setFile] = useState(null);
  const [nome, setNome] = useState("");
  const [sobrenome, setSobrenome] = useState("");
  const [cpf, setCpf] = useState("");
  const [nascimento, setNascimento] = useState("");
  const [email, setEmail] = useState("");
  const [telefone, setTelefone] = useState("");

  const getDadosFormSurdoId = (id) => {
    return (dispatch) => {
      api.get(`/surdo/${id}`).then((response) => {
        const string = JSON.stringify(response.data);
        const surdo = JSON.parse(string);
        setNome(surdo.nome);
        setSobrenome(surdo.sobrenome);
        setCpf(surdo.cpf);
        setTelefone(surdo.telefone);
        setNascimento(surdo.dataNascimento);
        setEmail(surdo.email);
        console.log(surdo);
      });
    };
  };

  const getUserId = () => {
    return localStorage.getItem("idSurdo");
  };

  const getDadosFormSurdos = (username) => {
    return () => {
      api.get("/surdo").then((response) => {
        const surdos = response.data;
        surdos.forEach((element) => {
          if (element.email === username) {
            console.log(element.id);
          }
        });
      });
    };
  };

  useEffect(() => {
    dispatch(getDadosFormSurdoId(getUserId()));
  }, []);

  const onSubmit = (data) => {
    const payload = {
      cpf: data.cpf,
      nome: data.nome,
      sobrenome: data.sobrenome,
      dataNascimento: data.dataNascimento,
      role: "SURDO",
      telefone: data.telefone,
      email: data.email,
      senha: data.senha,
    };
    console.log(payload);
    dispatch(putDadosFormSurdo(getUserId(), payload));
  };

  return (
    <div className="form-container">
      <div className="form-container-line">
        <div className="form-group">
          <label>Nome</label>
          <input
            value={nome}
            className={errors?.nome && "input-error"}
            type="text"
            placeholder="Escreva seu nome"
            {...register("nome", {
              onChange: (e) => {
                setNome(e.target.value);
              },
              required: false,
            })}
          />
          {errors?.nome?.type === "required" && (
            <p className="error-message">Nome é um campo obrigatório.</p>
          )}
        </div>

        <div className="form-group">
          <label>Sobrenome</label>
          <input
            value={sobrenome}
            className={errors?.sobrenome && "input-error"}
            type="text"
            placeholder="Escreva seu sobrenome"
            {...register("sobrenome", {
              onChange: (e) => {
                setSobrenome(e.target.value);
              },
              required: false,
            })}
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
            disabled
            value={cpf}
            className={errors?.cpf && "input-error"}
            type="text"
            placeholder="Escreva seu CPF"
            {...register("cpf", {
              onChange: (e) => {
                setCpf(e.target.value);
              },
              required: false,
            })}
          />
          {errors?.cpf?.type === "required" && (
            <p className="error-message">CPF é um campo obrigatório.</p>
          )}
        </div>

        <div className="form-group">
          <label>Data de Nascimento</label>
          <input
            value={nascimento}
            className={errors?.dataNascimento && "input-error"}
            type="date"
            placeholder="Escreva sua Data de Nascimento"
            {...register("dataNascimento", {
              onChange: (e) => {
                setNascimento(e.target.value);
              },
              required: false,
            })}
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
            disabled
            value={email}
            className={errors?.email && "input-error"}
            type="email"
            placeholder="seuemail@mail.com"
            {...register("email", {
              onChange: (e) => {
                setEmail(e.target.value);
              },
              required: false,
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
            value={telefone}
            className={errors?.telefone && "input-error"}
            type="text"
            placeholder="Escreva seu Telefone"
            {...register("telefone", {
              onChange: (e) => {
                setTelefone(e.target.value);
              },
              required: false,
            })}
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
            {...register("senha", {
              required: false,
            })}
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
            {...register("confirmaSenha", {
              required: false,
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
            disabled
            className={errors?.documento && "input-error"}
            type="file"
            {...register("file", {
              onChange: (e) => {
                setFile(e.target.files[0]);
              },
              required: false,
            })}
          />
          {errors?.documento?.type === "required" && (
            <p className="error-message">Documento é um campo obrigatório.</p>
          )}
        </div>
      </div>

      <div className="form-group">
        <button onClick={() => handleSubmit(onSubmit)()}>
          Atualizar dados
        </button>
      </div>
    </div>
  );
};

export default FormularioEditarSurdo;
