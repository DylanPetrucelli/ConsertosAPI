package br.edu.ifsp.prw3.av3.util.security;

import br.edu.ifsp.prw3.av3.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class PW3TokenService {

    @Value("${pw3.senha.principal.geracao.token}")
    private String secret;

    public String gerarToken(Usuario usuario) {

        try {
            var algoritmo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("DISCIPLINA PW3")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {

        System.out.println(tokenJWT);
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            JWTVerifier jwtv = JWT.require(algoritmo) //this may be the wrong import idk
                    .withIssuer("DISCIPLINA PW3")
                    .build();
            return jwtv.verify(tokenJWT).getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(10).toInstant(ZoneOffset.of("-03:00"));
    }
}