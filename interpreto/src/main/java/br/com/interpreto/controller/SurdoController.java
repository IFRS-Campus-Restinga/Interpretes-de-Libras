package br.com.interpreto.controller;

import br.com.interpreto.model.surdo.Surdo;
import br.com.interpreto.model.surdo.SurdoCadastroDTO;
import br.com.interpreto.service.SurdoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/surdo")
public class SurdoController {
    private final SurdoService surdoService;
    @Autowired
    public SurdoController(SurdoService surdoService){
        this.surdoService = surdoService;
    }
    @GetMapping
    public List<Surdo> listarSurdo(){
        return surdoService.listarSurdo();
    }
    @PostMapping
    public void cadastrarSurdo(@RequestBody @Valid SurdoCadastroDTO dados){
        surdoService.cadastrarSurdo(dados);
    }
    //FALTA O UPDATE E O DELETE
    @GetMapping("/{id}")
    public Optional<Surdo> buscarSurdo(@PathVariable Long id){
        return surdoService.buscarSurdo(id);
    }
    //FALTA O UPDATE E O DELETE
}
