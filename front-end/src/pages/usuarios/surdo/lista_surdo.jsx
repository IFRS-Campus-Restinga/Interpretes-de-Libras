import { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import api from "../../../services/api";
import StarRatings from "react-star-ratings";

const ListaSurdosLogados = () => {
  const dispatch = useDispatch();
  const [interpretes, setInterpretes] = useState([]);
  const [userRatings, setUserRatings] = useState([]);

  const [rating, setRatings] = useState(Array(userRatings.length).fill(''));

  const updateRating = (index, newRating) => {
    const newRatings = [...rating];
    newRatings[index] = newRating;
    setRatings(newRatings);
  };
  
  const getInterpretes = () => {
    return (dispatch) => {
      api.get(`/surdo`).then((response) => {
        console.log("/surdo", response.data);
        setInterpretes(JSON.parse(JSON.stringify(response.data)));
      });
    };
  };
  const postFeedback = (avaliado, index) => {
    const payload = {
      avaliador: parseInt(localStorage.getItem("id")),
      resenha: "Esta é uma resenha de exemplo.",
      nota: rating[index],
      avaliado: avaliado,
    };

    return (dispatch) => {
      api.post(`/feedback/criarFeedback`, payload).then((response) => {
        console.log("/surdo", response.data);
      });
    };
  };

  useEffect(() => {
    dispatch(getInterpretes());
  }, [dispatch]);
  return (
    <div className="perfil">
      {interpretes?.map((solicitacao, index) => {
        return (
          <div className="card">
            <div className="card-left">
              <div>ID do surdo: {solicitacao.id}</div>
              <div>Nome do surdo: {solicitacao.nome} </div>
              <div>E-mail surdo: {solicitacao.email} </div>
              <div>Telefone surdo: {solicitacao.telefone} </div>
              <div>
                Avaliar encontro:{" "}
                <StarRatings
                  rating={rating[index]}
                  starRatedColor="blue"
                  changeRating={(e) => updateRating(index, e)}
                  numberOfStars={5}
                  starDimension="15px"
                  name="rating"
                />
                <button className="feedback" onClick={postFeedback(solicitacao.id, index)}>Avaliar</button>
              </div>
            </div>
            <div className="card-rigth"></div>
          </div>
        );
      })}
    </div>
  );
};

export default ListaSurdosLogados;
