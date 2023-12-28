import "./formulario.css";
import {useState, useEffect} from "react";
import api from "../../../services/api";
import { useDispatch } from "react-redux";
const Tradutor = () => {
    const dispatch = useDispatch();
    const [nome, setNome] = useState("");
    const [telefone, setTelefone] = useState("");
    const [endereco, setEndereco] = useState("");
    const [especialidade, setEspecialidade] = useState("");
    const [tradutor, setTradutor] = useState([]);
    const [id, setId] = useState("");

    const cadastraTradutor = () => {
        const payload = {
            "nome" : nome,
            "telefone": telefone,
            "endereco": endereco,
            "especialidade": especialidade
        }
        return (dispatch) => {
          api.post(`/tradutor/cadastro`, payload).then((response) => {
            console.log(response.data);
            if(response.data.nome != null) {
                alert("Usuário cadastrado com sucesso!");
                setTradutor(JSON.parse(JSON.stringify(response.data)));
            }else{
                alert("Falha ao cadastrar usuário!");
            }
          });
        };
      };

      const getTradutor = () => {

              return (dispatch) => {
                api.get(`/tradutor`).then((response) => {
                  console.log(response.data);
                  setTradutor(JSON.parse(JSON.stringify(response.data)));
                });
              };
            };

    useEffect(() => {
        dispatch(getTradutor());
      }, [dispatch]);


    const handleSubmit = () => {
    const re = /^[a-zA-Z\b]+$/;
        if(nome.length >= 5 && nome.length <= 100){
            if (nome !== '' && re.test(nome)) {
                if(telefone != "" && endereco != "" && especialidade != ""){
                    dispatch(cadastraTradutor());
                }else{
                    alert("Preencha todos os campos!");
                }
            }else{
                alert("Campo nome com valor inválido!");
            }
        }
    }

    const atualizarUser = () => {
            const payload = {
                            "nome" : nome,
                            "telefone": telefone,
                            "endereco": endereco,
                            "especialidade": especialidade
                        }
                    api.put(`/tradutor/${id}`, payload).then((response) => {
                                                          console.log(response.data);
                                                          alert("Atualizado com sucesso");
                                                        });
          };

    const editaTradutor = (id) => {
        tradutor.map((e)=> {if(id === e.id){setId(e.id); setNome(e.nome);setTelefone(e.telefone); setEndereco(e.endereco); setEspecialidade(e.especialidade) }})

    }

    return (<div className="container">
        <div className="formulario">
        <h1>Cadastrar-se</h1>
        <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} placeholder="Nome do prestador" />
        <input type="text" value={telefone} onChange={(e) => setTelefone(e.target.value)} placeholder="Telefone"/>
         <input type="text" value={endereco} onChange={(e) => setEndereco(e.target.value)} placeholder="Endereço"/>
         <input type="text" value={especialidade} onChange={(e) => setEspecialidade(e.target.value)} placeholder="Especialidade"/>
        <button onClick={handleSubmit}>Cadastrar-se</button>
        <button onClick={atualizarUser} className="Atualizar">Atualizar</button>
        <table className="table">
                        {(tradutor != null) && tradutor.map((e)=> <tr onClick={()=> editaTradutor(e.id)}> <td> {e.id} </td> <td> {e.nome} </td> </tr> )}
                        </table>
        </div>
    </div>)


}
export default Tradutor;