import interprete from "../../../images/interprete.png";
import "./Perfil_interprete.css";
import FormularioEditarInterprete from "../../perfil/formularios/formulario-editar-interprete";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import api from "../../../services/api";
import StarRatings from "react-star-ratings";

const InterpreteHomePage = () => {
  const [rating, setRating] = useState(5);
  const dispatch = useDispatch();
  const changeRating = (rating) => {
    setRating(rating);
  };
  const getRating = (dispacth) => {
    return (dispacth) => {
      api
        .get("/usuario")
        .then((response) => {
          console.log(response.data);
          getUsuario(JSON.parse(JSON.stringify(response.data)));
        })
        .catch((error) => {
          console.log(error.data);
        });
    };
  };

  const getUsuario = (usuario) => {
    console.log(localStorage.getItem("id"));
    const userId = parseInt(localStorage.getItem("id"));
    console.log(usuario);
    const objectWithId4 = usuario.find((item) => item.id === userId);
    console.log(objectWithId4);
    console.log(objectWithId4.nota);
    setRating(objectWithId4.nota);
  };
  useEffect(() => {
    dispatch(getRating());
  }, [dispatch]);
  return (
    <>
      <div className="perfil">
        <div className="fotoPerfil">
          <img src={interprete} alt="Foto de perfil do usuário" />
          <h1>INTERPRETE</h1>
          <p>@interprete_123</p>
          <StarRatings
            rating={rating}
            starRatedColor="blue"
            changeRating={(e) => changeRating(e)}
            numberOfStars={5}
            starDimension="25px"
            name="rating"
          />
        </div>
        <div className="editarPerfil">
          <h1>Configuração do perfil</h1>
          <FormularioEditarInterprete />
        </div>
      </div>
    </>
  );
};

export default InterpreteHomePage;
