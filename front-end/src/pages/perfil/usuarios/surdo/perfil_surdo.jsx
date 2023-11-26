import "./Perfil.css";
import FormularioEditarSurdo from "./formularios/formulario-editar-surdo";

import surdo from "../../images/surdo.png";

const Perfil = (email) => {
  return (
    <div className="perfil">
      <div className="fotoPerfil">
        <img src={surdo} alt="Foto de perfil do usuário" />
        <h1>SURDO</h1>
        <p>@surdo_123</p>
      </div>
      <div className="editarPerfil">
        <h1>
            Configuração do perfil
        </h1>
        <FormularioEditarSurdo id={email}/>
      </div>
    </div>
  );
};

export default Perfil;
