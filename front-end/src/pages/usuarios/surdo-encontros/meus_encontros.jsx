import surdo from "../../../images/surdo.png";
import ListaInterpretesLogados from "../interprete/lista_interepretes";
import HeaderSurdo from "../../../components/header-surdo/header-surdo";
import "../surdo-encontros/surdos-encontros.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const MeusEncontros = () => {
  const [search, setSearch] = useState("");
  const navigate = useNavigate();
  const handleClick = () => {
    localStorage.setItem("filtro", search);
    navigate("/surdo/encontros");
  }
  const maisRecente = () => {
    localStorage.setItem("maisRecente", true);
    navigate("/surdo/encontros");
  }
  return (
    <>
      <HeaderSurdo />
      <div className="perfil">
        <div className="fotoPerfil">
          <div>
            <img src={surdo} alt="Foto de perfil do usuário" />
            <h1>SURDO</h1>
            <p>@surdo_123</p>
          </div>
          <div className="divider-25"></div>
          <div className="filters">
            <h1>Filtros de pesquisa</h1>
            <div className="search">
              <input
                  type="text"
                  value={search}
                  onChange={(e) => setSearch(e.target.value)}
                  placeholder="Insira a sua pesquisa"
                />
                <button onClick={handleClick}>🔍</button>
            </div>
            <p>
              Escolher filtros automaticamente refina e atualiza a pesquisa dos
              itens.
            </p>
          </div>
        </div>
        <div className="editarPerfil">
          <h1>Lista de encontros</h1>
          <ListaInterpretesLogados/>
        </div>
      </div>
    </>
  );
};

export default MeusEncontros;
