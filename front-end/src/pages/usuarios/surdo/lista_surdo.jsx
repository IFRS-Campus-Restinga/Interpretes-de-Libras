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

  const openWhatsApp = (telefone) => {
    window.open(
      `https://api.whatsapp.com/send?phone=55${telefone}e&text=Ol%C3%A1,%20quero%20fazer%20algumas%20combina%C3%A7%C3%B5es%20com%20voc%C3%AA!%20`,
      "_blank"
    );
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
      nota: rating[index] ?? 5,
      avaliado: avaliado,
    };

    return (dispatch) => {
      api.post(`/feedback/criarFeedback`, payload).then((response) => {
        console.log("/surdo", response.data);
        alert(response.data);
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
              <div>E-mail do surdo: {solicitacao.email} </div>
              <div>
                WhatsApp do surdo:{" "}
                <span className="goToWhatssAppButton" onClick={() => openWhatsApp(solicitacao.telefone)}>
                  {solicitacao.telefone}
                </span>{" "}
              </div>
              <div>
                Avaliar encontro:{" "}
                <StarRatings
                  rating={rating[index] ?? 5}
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
