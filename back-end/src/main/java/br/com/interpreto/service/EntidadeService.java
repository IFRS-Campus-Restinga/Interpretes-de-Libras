package br.com.interpreto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.interpreto.status.Entidade;
import br.com.interpreto.status.EntidadeRepository;
import br.com.interpreto.status.EntidadeSpecifications;
import br.com.interpreto.status.Status;

@Service
public class EntidadeService {

    @Autowired
    private EntidadeRepository entidadeRepository;

    public List<Entidade> getEntidadesByFiltro(Status status) {
        Specification<Entidade> specification = Specification.where(EntidadeSpecifications.hasStatus(status));
        return entidadeRepository.findAllById((Iterable<Long>) specification);
    }

    // Outros métodos de serviço conforme necessário
}
