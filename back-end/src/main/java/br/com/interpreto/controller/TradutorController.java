package br.com.interpreto.controller;

import br.com.interpreto.model.Tradutor;
import br.com.interpreto.model.TradutorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tradutor")
@CrossOrigin(origins = "*")
public class TradutorController {

    @Autowired
    private TradutorRepository tradutorRepository;

    @PostMapping("/cadastro")
    public String cadastrar(@RequestBody String tradutorJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Tradutor tradutor = mapper.readValue(tradutorJson, Tradutor.class);
        tradutorRepository.save(tradutor);
        return tradutorJson;
    }

    @PutMapping("/{id}")
    public Tradutor atualizarInterprete(@PathVariable Long id,
                                        @RequestBody @Valid String novosDados) throws JsonProcessingException {
        Tradutor tradutor = tradutorRepository.getReferenceById(id);
        ObjectMapper mapper = new ObjectMapper();
        Tradutor tradutor2 = mapper.readValue(novosDados, Tradutor.class);
        tradutor.setNome(tradutor2.getNome());
        tradutor.setTelefone(tradutor2.getTelefone());
        tradutor.setEndereco(tradutor2.getEndereco());
        tradutor.setEspecialidade(tradutor2.getEspecialidade());
        return tradutorRepository.save(tradutor);

    }

    @GetMapping
    public ResponseEntity<List<Tradutor>> listaTradutor() {
        List<Tradutor> listagem = tradutorRepository.findAll();

        List<Tradutor> listagemDTO = new ArrayList<>();
        for (Tradutor tradutor : listagem) {
            listagemDTO.add(new Tradutor(tradutor.getId(), tradutor.getNome(), tradutor.getEndereco(), tradutor.getTelefone(), tradutor.getEspecialidade()));
        }

        return ResponseEntity.ok(listagemDTO);
    }

}
