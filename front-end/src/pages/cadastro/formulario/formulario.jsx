import { useForm } from "react-hook-form";
import { isEmail } from "validator";
import { useDispatch } from "react-redux";
import { postSocilicitacaoCadastro } from "../../../store/fecthActions";

import "./formulario.css";

const Formulario = () => {
  const dispatch = useDispatch();

  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const watchPassword = watch("password");

  const onSubmit = (data) => {
    console.log("data", data);
    //adiciona dado no store de solicitações cadastro
    dispatch(postSocilicitacaoCadastro(data));
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
            type="text"
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
            placeholder="Your e-mail"
            {...register("email", {
              required: true,
              validate: (value) => isEmail(value),
            })}
          />
          {errors?.email?.type === "required" && (
            <p className="error-message">Email is required.</p>
          )}

          {errors?.email?.type === "validate" && (
            <p className="error-message">Email is invalid.</p>
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
          <label>Estado</label>
          <select
            className={errors?.estado && "input-error"}
            defaultValue="0"
            {...register("estado", { validate: (value) => value !== "0" })}
          >
            <option value="0">Selecione seu estado</option>
            <option value="RS">Rio Grande do Sul</option>
            <option value="OUTRO">Outro</option>
          </select>

          {errors?.estado?.type === "validate" && (
            <p className="error-message">Estado é um campo obrigatório.</p>
          )}
        </div>

        <div className="form-group">
          <label>Cidade</label>
          <select
            className={errors?.cidade && "input-error"}
            defaultValue="0"
            {...register("cidade", { validate: (value) => value !== "0" })}
          >
            <option value="0">Selecione sua cidade</option>
            <option value="RS">Porto Alegre</option>
            <option value="OUTRO">Outro</option>
          </select>

          {errors?.cidade?.type === "validate" && (
            <p className="error-message">Cidade é um campo obrigatório.</p>
          )}
        </div>
      </div>

      <div className="form-container-line">
        <div className="form-group">
          <label>Senha</label>
          <input
            className={errors?.password && "input-error"}
            type="password"
            placeholder="Password"
            {...register("password", { required: true, minLength: 7 })}
          />

          {errors?.password?.type === "required" && (
            <p className="error-message">Senha é um campo obrigatório.</p>
          )}

          {errors?.password?.type === "minLength" && (
            <p className="error-message">
              A senha precisa ter no mínimo 7 caracteres.
            </p>
          )}
        </div>

        <div className="form-group">
          <label>Confirmação de Senha</label>
          <input
            className={errors?.passwordConfirmation && "input-error"}
            type="password"
            placeholder="Repeat your password"
            {...register("passwordConfirmation", {
              required: true,
              validate: (value) => value === watchPassword,
            })}
          />
          {errors?.passwordConfirmation?.type === "required" && (
            <p className="error-message">
              Confirmação de Senha é um campo obrigatório.
            </p>
          )}

          {errors?.passwordConfirmation?.type === "validate" && (
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
            placeholder="Escreva o valor do seu atendimento por hora."
            {...register("valorHora", { required: true })}
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
          <label>Região</label>
          <div className="checkbox-group">
            <input
              type="checkbox"
              name="sul"
              value="SUL"
              {...register("sul")}
            />
            <label>Sul</label>
          </div>
          <div className="checkbox-group">
            <input
              type="checkbox"
              name="norte"
              value="NORTE"
              {...register("norte")}
            />
            <label>Norte</label>
          </div>
          <div className="checkbox-group">
            <input
              type="checkbox"
              name="leste"
              value="LESTE"
              {...register("leste")}
            />
            <label>Leste</label>
          </div>
          <div className="checkbox-group">
            <input
              type="checkbox"
              name="oeste"
              value="OESTE"
              {...register("oeste")}
            />
            <label>Oeste</label>
          </div>
          <div className="checkbox-group">
            <input
              type="checkbox"
              name="centro"
              value="CENTRO"
              {...register("centro")}
            />
            <label>Centro</label>
          </div>
        </div>

        <div className="form-group">
          <label>Especialidades</label>
          <div className="checkbox-group">
            <input type="checkbox" name="ti" value="TI" {...register("ti")} />
            <label>TI</label>
          </div>
          <div className="checkbox-group">
            <input
              type="checkbox"
              name="medicina"
              value="MEDICINA"
              {...register("medicina")}
            />
            <label>Medicina</label>
          </div>
          <div className="checkbox-group">
            <input
              type="checkbox"
              name="literatura"
              value="LITERATURA"
              {...register("literatura")}
            />
            <label>Literatura</label>
          </div>
          <div className="checkbox-group">
            <input
              type="checkbox"
              name="engenharia"
              valeu="ENGENHARIA"
              {...register("engenharia")}
            />
            <label>Engenharia</label>
          </div>
          <div className="checkbox-group">
            <input
              type="checkbox"
              name="humanas"
              value="HUMANAS"
              {...register("humanas")}
            />
            <label>Humanas</label>
          </div>
        </div>
      </div>

      <div className="form-group">
        <button onClick={() => handleSubmit(onSubmit)()}>Criar conta</button>
      </div>
    </div>
  );
};

export default Formulario;
