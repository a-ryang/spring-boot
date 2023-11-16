package com.aryang.todo.global.utils.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    // 32byte, 32자
    // ref :https://datatracker.ietf.org/doc/html/rfc7518#section-3.2
    private final String secret;

    public JwtUtil(
        @Value("${jwt.secret}") final String secret
    ) {
        this.secret = secret;
    }

    public String generateToken(Map<String, Object> claims, Date expirationDate) {
        return Jwts.builder()
            .claims(claims)
            .issuedAt(new Date())
            .expiration(expirationDate)
            .signWith(getSecretKey())
            .compact();
    }

    private Key getSecretKey() {
        byte[] secretKeyBytes = Base64.getEncoder().encode(secret.getBytes());
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }

}
