package br.com.interpreto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.interpreto.service.UsuarioService;

@RestController
@Controller
public class LoginController {
    @Autowired
    private UsuarioService usuarioService;
    /*
    @PostMapping("/login")
    public String processoLogin(String username, String password) {
        if (usuarioService.autenticarUsuario(username, password)) {
            // Lógica para redirecionar o usuário após o login bem-sucedido.
            return "redirect:/home";
        } else {
            // Lógica para lidar com falha na autenticação, por exemplo, exibindo uma mensagem de erro.
            return "redirect:/login?error";
        }
    }*/
}
