package br.com.interpreto.controller;

import br.com.interpreto.model.usuario.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import br.com.interpreto.service.UsuarioService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;

    @Autowired //INJECAO DE DEPENDENCIA VIA CONSTRUTOR
    public LoginController(UsuarioService usuarioService, AuthenticationManager authenticationManager){
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginDTO dados) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

}
