package br.com.interpreto.service;

import br.com.interpreto.model.surdo.SurdoDetalhamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.interpreto.model.surdo.Surdo;
import br.com.interpreto.model.usuario.Usuario;
import br.com.interpreto.model.usuario.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired //INJECAO DE DEPENDENCIA VIA CONSTRUTOR
    UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<List<Usuario>> listarUsuario() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }
}
