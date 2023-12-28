import interprete from "../../../images/interprete.png";
import ListaSurdosLogados from "../surdo/lista_surdo";
import HeaderInterprete from "../../../components/header-interprete/header-interprete";
const MeusEncontrosInterprete = () => {
  return (
    <>
      <HeaderInterprete />
      <div className="perfil">
        <div className="fotoPerfil">
          <img src={interprete} alt="Foto de perfil do usuário" />
          <h1>INTERPRETE</h1>
          <p>@interprete_123</p>
        </div>
        <div className="editarPerfil">
          <h1>Lista de encontros</h1>
          <ListaSurdosLogados />
        </div>
      </div>
    </>
  );
};

export default MeusEncontrosInterprete;
