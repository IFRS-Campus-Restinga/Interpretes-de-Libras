const initial_state = [
  {
    nome: "adrian",
    endereco: "Rua TANANA, 2020. POA/RS",
    telefone: "51 998765431",
    email: "adrian@restinga.ifrs.edu.br",
  },
  {
    nome: "pedro",
    endereco: "Rua TANANA, 2020. POA/RS",
    telefone: "51 998765431",
    email: "pedro@restinga.ifrs.edu.br",
  },
];

// eslint-disable-next-line import/no-anonymous-default-export
export default (state = initial_state, action) => {
  switch (action.type) {
    case "SHOW_PERFIL":
      return [...state, action.perfil];
    default:
      return state;
  }
};

//receber um objeto do backend que passará por um converter e irá renderizar o perfil do usuário.
export const showPerfil = (perfil) => {
  return {
    type: "SHOW_PERFIL",
    perfil,
  };
};

//fazer uma action para editar o perfil, ele nao pode ser convertida para ui
//como vamos passar isso para o form? a dúvida é em relacao ao hook do react-form-hook
