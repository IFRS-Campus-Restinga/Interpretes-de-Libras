package br.com.interpreto.model.feedback;


public record FeedbackLista(Long id, Long avaliador, Long idAvaliado, String nomeAvaliador, String nomeAvaliado, Double nota, String resenha) {

    public FeedbackLista(Feedback feedback) {
        this(
                feedback.getId(),
                feedback.getIdAvaliador(),
                feedback.getUsuario().getId(),
                feedback.getNomeAvaliador(),
                feedback.getNomeAvaliado(),
                feedback.getNota(),
                feedback.getResenha()
        );
    }
}

