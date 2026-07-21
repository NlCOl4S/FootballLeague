package com.futbolliga.service;

import com.futbolliga.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    private Claims extractAllClaims(final String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(final String token) {
        return extractAllClaims(token).getSubject();
    }

    //Genera el Access Token para el usuario
    public String generateToken(final User user) {
        return buildToken(user, jwtExpiration);
    }

    public String generateRefreshToken(final User user) {
        return buildToken(user, refreshExpiration);
    }

    //Construye el token
    private String buildToken(final User user, final long expiration) {
        return Jwts.builder()
                .id(user.getId().toString())
                .claims(Map.of("name", user.getName()))
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    //Revisa que el usuario no este usando un token vencido
    public boolean isTokenValid(final String token, final User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getEmail())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }


    private Date extractExpiration(final String token) {
        return extractAllClaims(token).getExpiration();
    }

    //Decodifica la clave secreta desde application.properties para la firma HMAC
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}