import "./Perfil.css";
import FormularioEditarInterprete from "./formularios/formulario-editar-interprete";

import interprete from "../../images/interprete.png";

const Perfil = () => {
  return (
    <div className="perfil">
      <div className="fotoPerfil">
        <img src={interprete} alt="Foto de perfil do usuário" />
        <h1>Sou interprete</h1>
        <p>@interprete_2023</p>
      </div>
      <div className="editarPerfil">
        <h1>
            Configuração do perfil
        </h1>
        <FormularioEditarInterprete />
      </div>
    </div>
  );
};

export default Perfil;
