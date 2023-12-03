import CardSolicitacao from "../../components/solicitacoes-cadastro/card-solicitacoes";
import { useSelector, useDispatch } from "react-redux";
import { useEffect } from "react";
import { getAllSocilicitacoesCadastro } from "../../store/fecthActions/index";

const ListaSolicitacoesDeCadastro = () => {
  const solicitacoes = useSelector((state) => state.solicitacoes);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getAllSocilicitacoesCadastro());
  }, [dispatch]);

  return (
    <div>
      {Array.from(Array(5), (element, index) => {
        return (
          <CardSolicitacao
            key={"0"}
            id={"0"}
            dataCriacao={"1998"}
            nome={"122313"}
            telefone={"455"}
            email={"123"}
            status={"231"}
          />
        );
      })}
    </div>
  );
};

export default ListaSolicitacoesDeCadastro;
