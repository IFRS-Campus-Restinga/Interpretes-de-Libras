import surdo from "../../../images/surdo.png";
import ListaInterpretesLogados from "../interprete/lista_interepretes";
import HeaderSurdo from "../../../components/header-surdo/header-surdo";
const MeusEncontros = () => {
  return (
    <>
      <HeaderSurdo />
      <div className="perfil">
        <div className="fotoPerfil">
          <img src={surdo} alt="Foto de perfil do usuário" />
          <h1>SURDO</h1>
          <p>@surdo_123</p>
        </div>
        <div className="editarPerfil">
          <h1>Lista de encontros</h1>
          <ListaInterpretesLogados />
        </div>
      </div>
    </>
  );
};

export default MeusEncontros;
