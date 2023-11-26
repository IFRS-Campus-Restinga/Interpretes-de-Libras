import { Link } from "react-router-dom";
import "./Cadastro.css";

import surdo from "../../images/surdo.png";
import interprete from "../../images/interprete.png";
import Header from "../../components/header/header";

const Cadastro = () => {
  return (
    <>
      <Header />
      <div className="cadastro">
        <div className="souSurdo">
          <img src={surdo} alt="Imagem de um usuário do sexo masculino" />
          <Link to="/cadastro/formulario/surdo">
            <button>Sou surdo</button>
          </Link>
        </div>
        <div className="souInterprete">
          <img src={interprete} alt="Imagem de um usuário do sexo masculino" />
          <Link to="/cadastro/formulario/interprete">
            <button>Sou interprete</button>
          </Link>
        </div>
      </div>
    </>
  );
};

export default Cadastro;
