package br.com.interpreto.status;

import org.springframework.data.jpa.domain.Specification;

public class EntidadeSpecifications {

    public static Specification<Entidade> hasStatus(Status status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    // Outros métodos de filtro conforme necessário
}
