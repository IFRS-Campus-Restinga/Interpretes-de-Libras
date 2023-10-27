package br.com.interpreto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.interpreto.model.surdo.Surdo;
import br.com.interpreto.model.usuario.Usuario;
import br.com.interpreto.model.usuario.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	

    public boolean autenticarUsuario(String username, String password) {
        //verificar no banco de dados).
    	Usuario usuario = usuarioRepository.findByUsername(username);
    	//Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && usuario.getSenha().equals(password)) {
            return true; // senha valida.
        }
        return false; // senha invalida.
    }
    
//    public boolean autenticarSurdo(String username, String password) {
//        //verificar no banco de dados).
//    	Surdo surdo = usuarioRepository.findByUsername(username);
//    	//Usuario usuario = usuarioRepository.findByEmail(email);
//        if (surdo != null && surdo.getSenha().equals(password)) {
//            return true; // senha valida.
//        }
//        return false; // senha invalida.
//    }
}
