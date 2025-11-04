package br.com.fiap.fhai.autenticacao.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "fhai-secret-key-muito-segura-com-mais-de-256-bits-para-hs256";
    private static final long EXPIRATION_TIME = 86400000;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String gerarToken(int userId, String email, String nome) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("nome", nome);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extrairEmail(String token) {
        return extrairClaims(token).getSubject();
    }

    public Integer extrairUserId(String token) {
        return extrairClaims(token).get("userId", Integer.class);
    }

    public String extrairNome(String token) {
        return extrairClaims(token).get("nome", String.class);
    }

    private Claims extrairClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenExpirado(String token) {
        return extrairClaims(token).getExpiration().before(new Date());
    }

    public boolean validarToken(String token, String email) {
        final String tokenEmail = extrairEmail(token);
        return (tokenEmail.equals(email) && !tokenExpirado(token));
    }
}