package com.assignment.todoapp.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private String jwtSecret = "9b42d1ebb41e2285ebfb1684636aa61444ee8cadf517669d7b8d9c18c321f94ac85612c145b9e05725f8a1d6e4a268883bf2ad3e6d8307d6286b6c66fcf3ca25ac978957bbfae449bb38834576821478b3ab0d14b9e1c4afff06ad362d0c14e2d276465cea625445438bcab6f6bfd45fb07bf119a6d1cbfc513bfed1eb71ab3c";
    private int jwtExpirationMs = 86400000;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
