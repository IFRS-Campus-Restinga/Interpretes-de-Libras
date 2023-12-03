import surdo from "../../../images/surdo.png";
import FormularioEditarSurdo from "../../perfil/formularios/formulario-editar-surdo";

const SurdoHomePage = () => {
  return (
    <div className="perfil">
      <div className="fotoPerfil">
        <img src={surdo} alt="Foto de perfil do usuário" />
        <h1>SURDO</h1>
        <p>@surdo_123</p>
      </div>
      <div className="editarPerfil">
        <h1>Configuração do perfil</h1>
        <FormularioEditarSurdo />
      </div>
    </div>
  );
};

export default SurdoHomePage;
