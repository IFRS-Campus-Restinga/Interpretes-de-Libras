import interprete from "../../../../images/interprete.png";
import "../../usuarios/interprete/Perfil_interprete.css";
import FormularioEditarInterprete from "../../formularios/formulario-editar-interprete";

const PerfilInterprete = () => {
  return (
    <div className="perfil">
      <div className="fotoPerfil">
        <img src={interprete} alt="Foto de perfil do usuário" />
        <h1>INTERPRETE</h1>
        <p>@interprete_123</p>
      </div>
      <div className="editarPerfil">
        <h1>
            Configuração do perfil
        </h1>
        <FormularioEditarInterprete/>
      </div>
    </div>
  );
};

export default PerfilInterprete;
