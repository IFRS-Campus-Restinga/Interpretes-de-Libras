package br.com.interpreto.controller;

import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.interprete.InterpreteCadastroDTO;
import br.com.interpreto.model.surdo.Surdo;
import br.com.interpreto.service.InterpreteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/interprete")
public class InterpreteController {
    private final InterpreteService interpreteService;
    @Autowired
    public InterpreteController(InterpreteService interpreteService){
        this.interpreteService = interpreteService;
    }
    @GetMapping
    public List<Interprete> listarInterprete(){
        return interpreteService.listarInterprete();
    }
    @PostMapping
    public void cadastrarInterprete(@RequestBody @Valid InterpreteCadastroDTO dados){
        interpreteService.cadastrarInterprete(dados);
    }
    @GetMapping("/{id}")
    public Optional<Interprete> buscarInterprete(@PathVariable Long id){
        return interpreteService.buscarInterprete(id);
    }
    //FALTA O UPDATE E O DELETE
}
