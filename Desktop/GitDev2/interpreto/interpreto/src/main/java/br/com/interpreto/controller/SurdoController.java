package br.com.interpreto.controller;

import br.com.interpreto.model.surdo.Surdo;
import br.com.interpreto.model.surdo.SurdoDTO;
import br.com.interpreto.model.surdo.SurdoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/surdo")
public class SurdoController {

    @Autowired
    private SurdoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid SurdoDTO dados){
        repository.save(new Surdo(dados));
    }
}
