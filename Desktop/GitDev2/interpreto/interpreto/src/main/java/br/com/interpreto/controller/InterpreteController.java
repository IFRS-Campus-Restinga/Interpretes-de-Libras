package br.com.interpreto.controller;

import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.interprete.InterpreteDTO;
import br.com.interpreto.model.interprete.InterpreteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interprete")
public class InterpreteController {

    @Autowired
    private InterpreteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid InterpreteDTO dados){
        repository.save(new Interprete(dados));
    }
}
