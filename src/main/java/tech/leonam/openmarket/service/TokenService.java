package tech.leonam.openmarket.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.model.entity.LoginEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public static final String ISSUER = "open-market";

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(LoginEntity entity) {
        return JWT.create().withIssuer(ISSUER).withSubject(entity.getCpf()).withExpiresAt(genExpirationDate()).sign(Algorithm.HMAC256(secret));
    }

    public String validationToken(String token) throws JWTVerificationException {
        return JWT.require(Algorithm.HMAC256(secret)).withIssuer(ISSUER).build().verify(token).getSubject();
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
