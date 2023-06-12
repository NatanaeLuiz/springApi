package com.estudo.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.estudo.api.entity.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60;

    public String gerarToken(Usuario user) {

        String token = JWT.create().
                withIssuer("auth0").
                withSubject(user.getLogin()).
                withClaim("id", user.getId()).
                withExpiresAt(LocalDateTime.now()
                        .plusSeconds(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("secret"));

        return token;
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("secret"))
                .withIssuer("auth0")
                .build().verify(token).getSubject();
    }
}
