package br.com.jrr.apiTest.infra.security;

import br.com.jrr.apiTest.domain.user.User;
import com.auth0.jwt.JWT;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(User user){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("Commom-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(dateExpiration())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error when generating JWT token", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Commom-api")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalid or Expired");
        }
    }

    private Instant dateExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}