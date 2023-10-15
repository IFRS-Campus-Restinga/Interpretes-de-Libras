package br.com.interpreto.service;

import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.interprete.InterpreteCadastroDTO;
import br.com.interpreto.model.interprete.InterpreteRepository;
import br.com.interpreto.model.surdo.Surdo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class InterpreteService {
    final private InterpreteRepository interpreteRepository;
    @Autowired
    public InterpreteService(InterpreteRepository interpreteRepository) {
        this.interpreteRepository = interpreteRepository;
    }
    @Transactional
    public void cadastrarInterprete(InterpreteCadastroDTO dados){
        interpreteRepository.save(new Interprete(dados));
    }
    public List<Interprete> listarInterprete(){
        return interpreteRepository.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Interprete> buscarInterprete(Long id) {
        return interpreteRepository.findById(id);
    }
}

