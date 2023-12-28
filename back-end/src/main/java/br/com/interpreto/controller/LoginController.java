package br.com.interpreto.controller;

import br.com.interpreto.model.usuario.LoginDTO;
import br.com.interpreto.model.usuario.LoginRespostaDTO;
import br.com.interpreto.model.usuario.Usuario;
import br.com.interpreto.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import br.com.interpreto.service.UsuarioService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginDTO dados) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginRespostaDTO(token, ((Usuario) auth.getPrincipal()).getRole().toString(),
                        ((Usuario) auth.getPrincipal()).getId().toString(), ((Usuario) auth.getPrincipal()).getAtivo()));
    }
}
