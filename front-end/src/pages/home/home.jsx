import "./Home.css";

import banner from "../../images/banner.png";
import facebook from "../../images/facebook.png";
import x from "../../images/twitter.png";
import instagram from "../../images/instagram.png";

function Home() {
  return (
    <>
      <div className="home">
        <div className="conteudoPrincipal">
          <div className="banner">
            <img
              src={banner}
              alt="Imagem de alguns estudantes se comunicando"
            />
          </div>
          <div className="about">
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Corrupti
            autem eius, tempore consequuntur possimus aspernatur enim maxime.
            Nam beatae accusamus aliquid quam impedit veniam quas, repellat rem
            ullam quisquam! Eligendi nulla sit placeat in inventore, perferendis
            corporis autem quo voluptates tenetur vitae nesciunt deserunt,
            magnam harum voluptatibus alias maxime velit ex totam minima. In,
            excepturi doloribus eveniet fugiat, enim sunt nisi voluptatum
            voluptatem soluta cum inventore illo, totam dolor? Consectetur
            necessitatibus, ea laborum, rem officiis assumenda quis repellendus
            minus fuga nostrum voluptas qui voluptate ab accusantium asperiores
            tempore ipsa ex autem quos dolor. Qui adipisci dolorem totam, maxime
            tempora accusantium.
          </div>
        </div>
        <div className="social">
          <img src={facebook} alt="Ícone do facebook" />
          <img src={x} alt="Ícone do X" />
          <img src={instagram} alt="Ícone do Instagram" />
        </div>
      </div>
    </>
  );
}

export default Home;
