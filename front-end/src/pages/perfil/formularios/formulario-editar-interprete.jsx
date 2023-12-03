import { useForm } from "react-hook-form";
import { isEmail } from "validator";
import { useState, useEffect } from "react";
import api from "../../../services/api";
import "./formulario.css";
import { useDispatch } from "react-redux";
import { putEditarPerfilInterprete } from "../../../store/fecthActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";

const FormularioEditarInterprete = ({ id }) => {
  const { registro, setValue } = useForm();
  const [cpf, setCpf] = useState("");
  const [nome, setNome] = useState("");
  const [sobrenome, setSobrenome] = useState("");
  const [telefone, setTelefone] = useState("");
  const [email, setEmail] = useState("");
  const [especialidades, setEspecialidade] = useState("");
  const [regioes, setRegioes] = useState("");
  const [senha, setSenha] = useState("");
  const [valorHora, setValorHora] = useState("");
  const [dataNascimento, setDataNascimento] = useState("");

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getDadosFormInterprete(getUserId()));
  }, [dispatch]);

  const getUserId = () => {
    return id;
  };

  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const getDadosFormInterprete = (id) => {
    return (dispatch) => {
      api.get(`/interprete/${id}`).then((response) => {
        const string = JSON.stringify(response.data);
        const interprete = JSON.parse(string);
        setCpf(interprete.cpf);
        setNome(interprete.nome);
        setSobrenome(interprete.sobrenome);
        setTelefone(interprete.telefone);
        setEmail(interprete.email);
        setDataNascimento(interprete.dataNascimento);
        setValorHora(interprete.valorHora);
        setRegioes(interprete.regioes);
        setEspecialidade(interprete.especialidades);
        console.log(interprete);
      });
    };
  };

  const watchPassword = watch("senha");

  const onSubmit = (data) => {
    console.log(data);
    const formData = new FormData();
    var regioes = [
      data.sul ? "SUL" : null,
      data.norte ? "NORTE" : null,
      data.centro ? "CENTRO" : null,
      data.leste ? "LESTE" : null,
      data.oeste ? "OESTE" : null,
    ];
    var especialidade = [
      data.ti ? "TI" : null,
      data.medicina ? "MEDICINA" : null,
      data.literatura ? "LITERATURA" : null,
      data.engenharia ? "ENGENHARIA" : null,
      data.humanas ? "HUMANAS" : null,
    ];
    const payload = {
      cpf: cpf,
      nome: nome,
      sobrenome: sobrenome,
      telefone: telefone,
      email: email,
      dataNascimento: dataNascimento,
      valorHora: valorHora,
      senha: senha,
      especialidades: especialidade,
      regioes: regioes,
    };
    console.log(payload);
    dispatch(putEditarPerfilInterprete(getUserId(), payload));
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
            className={errors?.sobrenome && "input-error"}
            type="text"
            value={sobrenome}
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
            type="text"
            value={cpf}
            placeholder="Ex: 000.000.000-00"
          />
        </div>

        <div className="form-group">
          <label>Data de Nascimento</label>
          <input
            className={errors?.dataNascimento && "input-error"}
            type="date"
            value={dataNascimento}
            placeholder="Escreva sua Data de Nascimento"
            {...register("dataNascimento", {
              onChange: (e) => {
                setDataNascimento(e.target.value);
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
            type="email"
            value={email}
            placeholder="seuemail@mail.com"
          />
        </div>

        <div className="form-group">
          <label>Telefone</label>
          <input
            className={errors?.telefone && "input-error"}
            type="text"
            value={telefone}
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
            value={senha}
            placeholder="Digite sua senha"
            {...register("senha", {
              onChange: (e) => {
                setSenha(e.target.value);
              },
              required: false,
              minLength: 7,
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
            {...register("senhaConfirmation", {
              required: false,
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
          <label>Valor da Hora</label>
          <input
            className={errors?.valorHora && "input-error"}
            type="text"
            value={valorHora}
            placeholder="Escreva o valor do seu atendimento por hora."
            {...register("valorHora", {
              onChange: (e) => {
                setValorHora(e.target.value);
              },
              required: false,
            })}
          />
          {errors?.valorHora?.type === "required" && (
            <p className="error-message">
              Valor da hora é um campo obrigatório.
            </p>
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
              required: false,
            })}
          />
          {errors?.documento?.type === "required" && (
            <p className="error-message">Documento é um campo obrigatório.</p>
          )}
        </div>
      </div>

      <div className="form-container-line-one">
        <div className="form-group">
          <label>Região</label>
          <div className="checkbox-group">
            <input type="checkbox" name="sul" {...register("sul")} />
            <label>Sul</label>
          </div>
          <div className="checkbox-group">
            <input type="checkbox" name="norte" {...register("norte")} />
            <label>Norte</label>
          </div>
          <div className="checkbox-group">
            <input type="checkbox" name="leste" {...register("leste")} />
            <label>Leste</label>
          </div>
          <div className="checkbox-group">
            <input type="checkbox" name="oeste" {...register("oeste")} />
            <label>Oeste</label>
          </div>
          <div className="checkbox-group">
            <input type="checkbox" name="centro" {...register("centro")} />
            <label>Centro</label>
          </div>
        </div>
        <div className="form-group">
          <label>Especialidades</label>
          <div className="checkbox-group">
            <input type="checkbox" name="ti" {...register("ti")} />
            <label>TI</label>
          </div>
          <div className="checkbox-group">
            <input type="checkbox" name="medicina" {...register("medicina")} />
            <label>Medicina</label>
          </div>
          <div className="checkbox-group">
            <input
              type="checkbox"
              name="literatura"
              {...register("literatura")}
            />
            <label>Literatura</label>
          </div>
          <div className="checkbox-group">
            <input
              type="checkbox"
              name="engenharia"
              {...register("engenharia")}
            />
            <label>Engenharia</label>
          </div>
          <div className="checkbox-group">
            <input type="checkbox" name="humanas" {...register("humanas")} />
            <label>Humanas</label>
          </div>
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

FormularioEditarInterprete.propTypes = {
  id: PropTypes.string.isRequired,
};

const mapStateToProps = (state) => ({
  id: state.perfil[0].id,
});

export default connect(mapStateToProps)(FormularioEditarInterprete);
