import { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import api from "../../../services/api";
import sendEmail from "../../../services/senEmail";
import StarRatings from "react-star-ratings";
import interprete from "../../../images/interprete.png";
import { useNavigate } from "react-router-dom";
import Modal from "react-modal";

const ListaInterpretesLogados = () => {
  const dispatch = useDispatch();
  const [interpretes, setInterpretes] = useState([]);
  const navigate = useNavigate();

  const [userRatings, setUserRatings] = useState([]);

  const [surdo, setSurdo] = useState();

  const [modalOpen, setModalOpen] = useState(false);

  const [searchIdSurdo, setSearchIdSurdo] = useState("");

  const [mensagem, setMensagem] = useState("");

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
  const getSurdo = () => {
    return (dispatch) => {
      api.get(`/surdo/${localStorage.getItem("id")}`).then((response) => {
        setSurdo(response.data);
        console.log(response.data);
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

  const getFiltro = () => {
    return localStorage.getItem("filtro");
  };

  const deleteInterprete = (id) => {
    return (dispatch) => {
      api.delete(`/interprete/${id}`).then((response) => {
        console.log("/interprete", response);
        window.location.href = "/surdo/encontros";
      });
    };
  };

  const modalIsOpen = () => {
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  const handleSerachSurdoById = () => {
    return (dispatch) => {
      api.get(`/interprete`).then((response) => {
        console.log("/interprete", response.data);
        if (toString(response.data.id) === localStorage.getItem("id")) {
          setInterpretes(JSON.parse(JSON.stringify(response.data)));
          setUserRatings(JSON.parse(JSON.stringify(response.data)));
        }
      });
    };
  };

  const handleSolicitacao = (solicitacao) => {
    console.log(solicitacao);
    const payload = {
      sender: { name: surdo.nome, email: surdo.email },
      to: [{ email: solicitacao.email, name: solicitacao.nome }],
      subject: "Solicitação de encontro",
      htmlContent: `<html><head></head><body><p>${mensagem}</p></body></html>`,
    };
    let axiosConfig = {
      headers: {
        "api-key":
          "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",
      },
    };
    return (dispatch) => {
      sendEmail
        .post(`/v3/smtp/email`, payload, axiosConfig)
        .then((response) => {
          console.log(response.data);
          (response.data.messageId != null) ? alert("E-mail enviado com sucesso!") : alert("Erro ao enviar email!");
        });
    };
  };
  const afterOpenModal = () => {};

  useEffect(() => {
    dispatch(getInterpretes());
    dispatch(getSurdo());
    getFiltro();
  }, [dispatch]);
  if (interpretes.length === 1) {
    return (
      <div className="encontros">
        <Modal
          isOpen={modalOpen}
          onAfterOpen={afterOpenModal}
          onRequestClose={closeModal}
          contentLabel="Example Modal"
          className="modal-content2"
          overlayClassName="modal-overlay"
        >
          <input
            type="text"
            placeholder="Insira o ID do surdo"
            value={searchIdSurdo}
            onChange={(e) => setSearchIdSurdo(e.target.value)}
          />
          <button onClick={handleSerachSurdoById}>Buscar</button>
        </Modal>
        <div className="noDataFound">
          Nenhum intreprete encontrado!
          <button onClick={modalIsOpen}>Encontrar interpretes</button>
        </div>
      </div>
    );
  } else {
    return (
      <div className="encontros">
        {interpretes?.map((solicitacao, index) => {
          if (solicitacao.id === 4) {
            return <></>;
          }
          if (
            solicitacao.nome != null &&
            solicitacao.telefone != null &&
            solicitacao.email != null
          ) {
            if (
              solicitacao.nome.includes(getFiltro()) ||
              solicitacao.telefone.includes(getFiltro()) ||
              solicitacao.email.includes(getFiltro())
            ) {
              return (
                <div className="card">
                  <Modal
                    isOpen={modalOpen}
                    onAfterOpen={afterOpenModal}
                    onRequestClose={closeModal}
                    contentLabel="Example Modal"
                    className="modal-content"
                    overlayClassName="modal-overlay"
                  >
                    <h2>Informações do encontro</h2>
                    <div>
                      Nome do interprete:{" "}
                      <strong>
                        {solicitacao.nome}
                        <br></br>
                      </strong>
                      Telefone do interprete:{" "}
                      <strong>
                        {solicitacao.telefone}
                        <br></br>
                      </strong>
                      E-mail do interprete: <strong>{solicitacao.email}</strong>
                    </div>
                    <h2>Enviar mensagem para o interprete</h2>
                    <textarea
                      className="mensagem"
                      value={mensagem}
                      onChange={(e) => setMensagem(e.target.value)}
                    ></textarea>
                    <button
                      className="enviarMensagem"
                      onClick={handleSolicitacao(solicitacao)}
                    >
                      Enviar solicitação
                    </button>
                  </Modal>
                  <img
                    className="fotoCard"
                    src={interprete}
                    alt="Foto do interprete"
                  ></img>
                  <p
                    className="deleteEncontro"
                    onClick={deleteInterprete(solicitacao.id)}
                  >
                    X
                  </p>
                  <div className="card-left">
                    <div>ID do interprete: {solicitacao.id}</div>
                    <div>Nome do interprete: {solicitacao.nome} </div>
                    <div>E-mail do interprete: {solicitacao.email} </div>
                    <div>
                      WhatsApp do interprete:{" "}
                      <span
                        className="goToWhatssAppButton"
                        onClick={() => openWhatsApp(solicitacao.telefone)}
                      >
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
                    <button className="meetAgain" onClick={modalIsOpen}>
                      Encontrar novamente
                    </button>
                  </div>
                  <div className="card-rigth"></div>
                </div>
              );
            } else if (getFiltro() === "") {
              return (
                <div className="card">
                  <Modal
                    isOpen={modalOpen}
                    onAfterOpen={afterOpenModal}
                    onRequestClose={closeModal}
                    contentLabel="Example Modal"
                    className="modal-content"
                    overlayClassName="modal-overlay"
                  >
                    <h2>Informações do encontro</h2>
                    <div>
                      Nome do interprete:{" "}
                      <strong>
                        {solicitacao.nome}
                        <br></br>
                      </strong>
                      Telefone do interprete:{" "}
                      <strong>
                        {solicitacao.telefone}
                        <br></br>
                      </strong>
                      E-mail do interprete: <strong>{solicitacao.email}</strong>
                    </div>
                    <h2>Enviar mensagem para o interprete</h2>
                    <textarea
                      className="mensagem"
                      value={mensagem}
                      onChange={(e) => setMensagem(e.target.value)}
                    ></textarea>
                    <button
                      className="enviarMensagem"
                      onClick={handleSolicitacao(solicitacao)}
                    >
                      Enviar solicitação
                    </button>
                  </Modal>
                  <img
                    className="fotoCard"
                    src={interprete}
                    alt="Foto do interprete"
                  ></img>
                  <p
                    className="deleteEncontro"
                    onClick={deleteInterprete(solicitacao.id)}
                  >
                    X
                  </p>
                  <div className="card-left">
                    <div>ID do interprete: {solicitacao.id}</div>
                    <div>Nome do interprete: {solicitacao.nome} </div>
                    <div>E-mail do interprete: {solicitacao.email} </div>
                    <div>
                      WhatsApp do interprete:{" "}
                      <span
                        className="goToWhatssAppButton"
                        onClick={() => openWhatsApp(solicitacao.telefone)}
                      >
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
                    <button className="meetAgain" onClick={modalIsOpen}>
                      Encontrar novamente
                    </button>
                  </div>
                  <div className="card-rigth"></div>
                </div>
              );
            }
          } else {
            return (
              <div className="card">
                <Modal
                  isOpen={modalOpen}
                  onAfterOpen={afterOpenModal}
                  onRequestClose={closeModal}
                  contentLabel="Example Modal"
                  className="modal-content"
                  overlayClassName="modal-overlay"
                >
                  <h2>Informações do encontro</h2>
                  <div>
                    Nome do interprete:{" "}
                    <strong>
                      {solicitacao.nome}
                      <br></br>
                    </strong>
                    Telefone do interprete:{" "}
                    <strong>
                      {solicitacao.telefone}
                      <br></br>
                    </strong>
                    E-mail do interprete: <strong>{solicitacao.email}</strong>
                  </div>
                  <h2>Enviar mensagem para o interprete</h2>
                  <textarea
                    className="mensagem"
                    value={mensagem}
                    onChange={(e) => setMensagem(e.target.value)}
                  ></textarea>
                  <button
                    className="enviarMensagem"
                    onClick={handleSolicitacao(solicitacao)}
                  >
                    Enviar solicitação
                  </button>
                </Modal>
                <img
                  className="fotoCard"
                  src={interprete}
                  alt="Foto do interprete"
                ></img>
                <p
                  className="deleteEncontro"
                  onClick={deleteInterprete(solicitacao.id)}
                >
                  X
                </p>
                <div className="card-left">
                  <div>ID do interprete: {solicitacao.id}</div>
                  <div>Nome do interprete: {solicitacao.nome} </div>
                  <div>E-mail do interprete: {solicitacao.email} </div>
                  <div>
                    WhatsApp do interprete:{" "}
                    <span
                      className="goToWhatssAppButton"
                      onClick={() => openWhatsApp(solicitacao.telefone)}
                    >
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
                  <button className="meetAgain" onClick={modalIsOpen}>
                    Encontrar novamente
                  </button>
                </div>
                <div className="card-rigth"></div>
              </div>
            );
          }
        })}
      </div>
    );
  }
};

export default ListaInterpretesLogados;
