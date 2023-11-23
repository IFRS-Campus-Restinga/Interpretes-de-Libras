package br.com.interpreto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.interpreto.status.Entidade;
import br.com.interpreto.status.EntidadeRepository;
import br.com.interpreto.status.Status;

@RestController
@RequestMapping("/entidade")
public class EntidadeController {

    @Autowired
    private EntidadeRepository entidadeRepository;

    @GetMapping
    public List<Entidade> getEntidadesByStatus(@RequestParam("status") Status status) {
        return entidadeRepository.findByStatus(status);
    }

    // Outros métodos do controlador conforme necessário
}
