package br.com.interpreto.service;

import br.com.interpreto.model.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String chaveSecreta;
    public String gerarToken(Usuario usuario){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(chaveSecreta);
            String token = JWT.create()
                    .withIssuer("interpreto")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algoritmo);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro na geração do Token JWT", exception);
        }
    }
    public String validacaoToken(String token){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(chaveSecreta);
            return JWT.require(algoritmo)
                    .withIssuer("interpreto")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "";
        }
    }
    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
