package br.com.interpreto.service;

import br.com.interpreto.model.surdo.Surdo;
import br.com.interpreto.model.surdo.SurdoDetalhamentoDTO;
import br.com.interpreto.model.usuario.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<List<Usuario>> listarUsuario() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }
    public ResponseEntity recuperarSenha(UsuarioRecuperarSenhaDTO dados){
        //Verificando se o usuário e senha estão corretos
        var usuario = usuarioRepository.findByEmailAndCpf(dados.email(), dados.cpf());

        if(usuario == null){
            return ResponseEntity.badRequest().body("Email ou cpf incorretos!");
        }else{
            return ResponseEntity.ok(new UsuarioDetalhamentoDTO(usuario));
        }
    }
    @Transactional
    public ResponseEntity trocarSenha(UsuarioTrocarSenhaDTO dados) {
        var usuario = usuarioRepository.getReferenceById(dados.id());
        //Encriptando a senha do usuário
        String senhaEncriptada = new BCryptPasswordEncoder().encode(dados.senha());
        usuario.setSenha(senhaEncriptada);
        //Salvando a senha do usuario
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().body("Troca de senha efetuada com sucesso!");
    }
    
    public List<UsuarioNota> FeedbackNotaPorId(Long id) {
		List<UsuarioNota> usuarioNota = usuarioRepository.findById(id).stream()
				.map(UsuarioNota::new).collect(Collectors.toList());

		return usuarioNota;
	}
    
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
