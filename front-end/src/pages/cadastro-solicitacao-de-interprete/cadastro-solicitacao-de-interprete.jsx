import { useForm } from "react-hook-form";
import "./formulario.css";
import { useDispatch } from "react-redux";
import { postCadastroSolicitacaoInterprete } from "../../store/fecthActions";


const FormularioSolicitacao = () => {
  const dispatch = useDispatch();
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const onSubmit = (data) => {

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

    var endereco = [
      data.rua,
      data.numero,
      data.cep,
      data.complemento,
      data.bairro,
      data.observacaoEndereco,
      data.pontoReferencia
    ];

    delete data.sul;
    delete data.norte;
    delete data.oeste;
    delete data.leste;
    delete data.centro;
    delete data.ti;
    delete data.medicina;
    delete data.literatura;
    delete data.engenharia;
    delete data.humanas;
    delete data.rua;
    delete data.numero;
    delete data.cep;
    delete data.complemento;
    delete data.bairro;
    delete data.observacaoEndereco;
    delete data.pontoReferencia;

    data["regioes"] = regioes;
    data["especialidades"] = especialidade;
    data["endereco"] = endereco;

    console.log(data)
    dispatch(postCadastroSolicitacaoInterprete(data));
  };

  return (
    <div>
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

      <div className="form-container-line-one">
        <div className="form-group">
          <label>Data do Encontro</label>
          <input
            className={errors?.dataEncontro && "input-error"}
            type="date"
            placeholder="Escreva sua Data do Encontro"
            {...register("dataEncontro", { required: true })}
          />
          {errors?.dataEncontro?.type === "required" && (
            <p className="error-message">
              Data de encontro é um campo obrigatório.
            </p>
          )}
        </div>

        <div className="form-group">
          <label>Hora do Encontro</label>
          <input
            className={errors?.hora && "input-error"}
            type="time"
            placeholder="Escreva a hora do encontro"
            {...register("hora", { required: true })}
          />
          {errors?.hora?.type === "required" && (
            <p className="error-message">
              Hora de encontro é um campo obrigatório.
            </p>
          )}
        </div>
      </div>

      <div className="form-container-line-one">
        <div className="form-group">
          <label>Observações</label>
          <input
            className={errors?.observacaoSolicitacao && "input-error"}
            type="text"
            placeholder="Escreva alguma observação se necessário"
            {...register("observacaoSolicitacao", { required: false })}
          />
        </div>
      </div>

      <div>
        <label className="form-container-title-end">Endereço</label>
        <div className="form-container-line-one">
          <div className="form-group">
            <label>Rua</label>
            <input
              className={errors?.rua && "input-error"}
              type="text"
              placeholder="Escreva a rua do encontro"
              {...register("rua", { required: true })}
            />
            {errors?.rua?.type === "required" && (
              <p className="error-message">Rua é um campo obrigatório.</p>
            )}
          </div>
          <div className="form-group">
            <label>Número</label>
            <input
              className={errors?.numero && "input-error"}
              type="text"
              placeholder="Escreva o número do endereço"
              {...register("numero", { required: true })}
            />
            {errors?.numero?.type === "required" && (
              <p className="error-message">Número é um campo obrigatório.</p>
            )}
          </div>

          <div className="form-group">
            <label>Complemento</label>
            <input
              className={errors?.complemento && "input-error"}
              type="text"
              placeholder="Escreva a rua do encontro"
              {...register("complemento", { required: false })}
            />
          </div>

        </div>
        <div className="form-container-line-one">
          <div className="form-group">
            <label>Bairro</label>
            <input
              className={errors?.bairro && "input-error"}
              type="text"
              placeholder="Escreva o bairro"
              {...register("bairro", { required: true })}
            />
            {errors?.bairro?.type === "required" && (
              <p className="error-message">Bairro é um campo obrigatório.</p>
            )}
          </div>

          <div className="form-group">
            <label>CEP</label>
            <input
              className={errors?.cep && "input-error"}
              type="text"
              placeholder="Escreva o CEP"
              {...register("cep", { required: true })}
            />
            {errors?.cep?.type === "required" && (
              <p className="error-message">CEP é um campo obrigatório.</p>
            )}
          </div>

        </div>
        <div className="form-container-line-one">
          <div className="form-group">
            <label>Observação Endereço</label>
            <input
              className={errors?.observacaoEndereco && "input-error"}
              type="text"
              placeholder="Escreva uma obervação para o endereço se necessário"
              {...register("observacaoEndereco", { required: false })}
            />
          </div>
        </div>


        <div className="form-container-line-one">
          <div className="form-group">
            <label>Ponto de Refência</label>
            <input
              className={errors?.pontoReferencia && "input-error"}
              type="text"
              placeholder="Escreva uma obervação para o endereço se necessário"
              {...register("pontoReferencia", { required: false })}
            />
          </div>
        </div>
      </div>

      <div className="form-group">
        <button onClick={() => handleSubmit(onSubmit)()}>Criar solicitação!</button>
      </div>
    </div>
  );
};

export default FormularioSolicitacao;
