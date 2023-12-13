import { useForm } from "react-hook-form";
import { isEmail } from "validator";
import { useState } from "react";
import axios from "axios";
import "./formulario.css";
import Header from "../../../components/header/header";

const Formulario = () => {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const cpfRegex = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;
  const valorHoraRegex = /^\d{1,3}\.\d{2}$/;

  const watchPassword = watch("senha");
  const [file, setFile] = useState(null);

  const onSubmit = (data) => {
    console.log(data);
    if (!file) {
      console.log("No files selected");
      return;
    }
    var regioes = [
      data.sul ? "SUL" : null,
      data.norte ? "NORTE" : null,
      data.oeste ? "OESTE" : null,
      data.leste ? "LESTE" : null,
      data.centro ? "CENTRO" : null,
    ];
    var especialidade = [
      data.ti ? "TI" : null,
      data.medicina ? "MEDICINA" : null,
      data.literatura ? "LITERATURA" : null,
      data.engenharia ? "ENGENHARIA" : null,
      data.humanas ? "HUMANAS" : null,
    ];
    delete data.sul;
    delete data.norte;
    delete data.oeste;
    delete data.leste;
    delete data.centro;
    delete data.password;
    delete data.passwordConfirmation;
    delete data.estado;
    delete data.cidade;
    delete data.file;
    delete data.ti;
    delete data.medicina;
    delete data.literatura;
    delete data.engenharia;
    delete data.humanas;
    data["regioes"] = regioes;
    data["especialidades"] = especialidade;
    data["role"] = "INTERPRETE";
    const json = JSON.stringify(data);
    const formData = new FormData();
    formData.append("arquivo", file);
    formData.append("dados", json);
    console.log("formData", formData);
    const options = {
      method: "POST",
      headers: { "Contnte-Type": "multipart/form-data" },
      data: formData,
      url: "http://localhost:8080/interprete",
    };
    axios(options)
      .then(function () {
        alert("Cadastro realizado com sucesso!");
      })
      .catch(function (error) {
        if(error.response.data.message != null){
          
          alert(error.response.data.message);
          console.log(error.response.data);
        }else{
          alert(error.response.data);
          console.log(error.response.data);
        }
      });
    console.log(options);
  };

  return (
    <>
      <Header />
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
              {...register("cpf", {
                required: true,
                pattern: {
                  value: cpfRegex,
                  message:
                    "Formato de CPF inválido. Use o formato 123.456.789-01.",
                },
              })}
            />
            {errors?.cpf?.type === "required" && (
              <p className="error-message">CPF é um campo obrigatório.</p>
            )}
            {errors?.cpf && (
              <p className="error-message">{errors.cpf.message}</p>
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
                validate: (value) => value !== watchPassword,
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
              placeholder="Escreva o valor do seu atendimento por hora."
              {...register("valorHora", {
                required: true,
                pattern: {
                  value: valorHoraRegex,
                  message: "Formato inválido. Use o formato 000.00.",
                },
              })}
            />
            {errors?.valorHora?.type === "required" && (
              <p className="error-message">
                Valor da hora é um campo obrigatório.
              </p>
            )}
            {errors?.valorHora && (
              <p className="error-message">{errors.valorHora.message}</p>
            )}
          </div>
        </div>

        <div className="form-container-line-one">
          <div className="form-group">
            <label>Documento</label>
            <input
              className={errors?.documento && "input-error"}
              type="file"
              accept="application/pdf"
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
              <input
                type="checkbox"
                name="medicina"
                {...register("medicina")}
              />
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
          <button onClick={() => handleSubmit(onSubmit)()}>Criar conta</button>
        </div>
      </div>
    </>
  );
};

export default Formulario;
