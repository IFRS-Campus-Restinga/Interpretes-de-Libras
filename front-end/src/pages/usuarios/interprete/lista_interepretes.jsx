import { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import api from "../../../services/api";
import StarRatings from "react-star-ratings";

const ListaInterpretesLogados = () => {
  const dispatch = useDispatch();
  const [interpretes, setInterpretes] = useState([]);

  const [userRatings, setUserRatings] = useState([]);

  const [rating, setRatings] = useState(Array(userRatings.length).fill(""));

  const updateRating = (index, newRating) => {
    const newRatings = [...rating];
    newRatings[index] = newRating;
    setRatings(newRatings);
  };

  const getInterpretes = () => {
    return (dispatch) => {
      api.get(`/interprete`).then((response) => {
        console.log("/interprete", response.data);
        setInterpretes(JSON.parse(JSON.stringify(response.data)));
        setUserRatings(JSON.parse(JSON.stringify(response.data)));
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
    console.log(payload);
    return (dispatch) => {
      api.post(`/feedback/criarFeedback`, payload).then((response) => {
        console.log("/interprete", response.data);
        alert(response.data);
      });
    };
  };
  const openWhatsApp = (telefone) => {
    window.open(
      `https://api.whatsapp.com/send?phone=55${telefone}e&text=Ol%C3%A1,%20quero%20fazer%20algumas%20combina%C3%A7%C3%B5es%20com%20voc%C3%AA!%20`,
      "_blank"
    );
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
              <div>ID do interprete: {solicitacao.id}</div>
              <div>Nome do interprete: {solicitacao.nome} </div>
              <div>E-mail do interprete: {solicitacao.email} </div>
              <div>
                WhatsApp do interprete:{" "}
                <span className="goToWhatssAppButton" onClick={() => openWhatsApp(solicitacao.telefone)}>
                  {solicitacao.telefone}
                </span>{" "}
              </div>
              <div>
                Avaliar encontro:{" "}
                <StarRatings
                  rating={rating[index] || 5}
                  starRatedColor="blue"
                  changeRating={(e) => updateRating(index, e)}
                  numberOfStars={5}
                  starDimension="15px"
                  name="rating"
                />
                <button
                  className="feedback"
                  onClick={postFeedback(solicitacao.id, index)}
                >
                  Avaliar
                </button>
              </div>
            </div>
            <div className="card-rigth"></div>
          </div>
        );
      })}
    </div>
  );
};

export default ListaInterpretesLogados;
