import surdo from "../../../images/surdo.png";
import FormularioEditarSurdo from "../../perfil/formularios/formulario-editar-surdo";
import StarRatings from "react-star-ratings";
import { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import api from "../../../services/api";

const SurdoHomePage = () => {
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
    <div className="perfil">
      <div className="fotoPerfil">
        <img src={surdo} alt="Foto de perfil do usuário" />
        <h1>SURDO</h1>
        <p>@surdo_123</p>
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
        <FormularioEditarSurdo />
      </div>
    </div>
  );
};

export default SurdoHomePage;
