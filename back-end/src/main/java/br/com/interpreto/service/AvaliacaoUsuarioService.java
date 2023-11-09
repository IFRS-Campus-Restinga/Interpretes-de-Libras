package br.com.interpreto.service;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioAtualizaDTO;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioCadastroDTO;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoUsuarioService {
    final private AvaliacaoUsuarioRepository avaliacaoUsuarioRepository;

    @Autowired //INJECAO DE DEPENDENCIA VIA CONSTRUTOR
    public AvaliacaoUsuarioService(AvaliacaoUsuarioRepository avaliacaoUsuarioRepository) {
        this.avaliacaoUsuarioRepository = avaliacaoUsuarioRepository;
    }

    @Transactional
    public void cadastrarAvaliacaoUsuario(AvaliacaoUsuarioCadastroDTO dados) {
        avaliacaoUsuarioRepository.save(new AvaliacaoUsuario(dados));
    }
    
    @GetMapping("/{id}")
    public Optional<AvaliacaoUsuario> ReceberResultadoSolicitacaoCadastro(Long id) {
        return avaliacaoUsuarioRepository.findById(id);
    }

    public List<AvaliacaoUsuario> listarAvaliacaoUsuario() {
        return avaliacaoUsuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<AvaliacaoUsuario> buscarAvaliacaoUsuario(Long id) {
        return avaliacaoUsuarioRepository.findById(id);
    }
    @Transactional
    public void atualizarAvaliacaoUsuario(Long id, AvaliacaoUsuarioAtualizaDTO novosDados) {
        Optional<AvaliacaoUsuario> opcionalAvalicaoUsuario = avaliacaoUsuarioRepository.findById(id);
        if (opcionalAvalicaoUsuario.isPresent()) {
            AvaliacaoUsuario avaliacaoUsuario = opcionalAvalicaoUsuario.get();
            avaliacaoUsuario.avaliacaoUsuarioAtualizarDTO(novosDados);
            avaliacaoUsuarioRepository.save(avaliacaoUsuario);
        }
    }
    @Transactional
    public void deletarAvaliacaoUsuario(Long id) {
        Optional<AvaliacaoUsuario> opcionalAvaliacaoUsuario = avaliacaoUsuarioRepository.findById(id);
        if (opcionalAvaliacaoUsuario.isPresent()) {
            avaliacaoUsuarioRepository.deleteById(id);
        }
    }
}