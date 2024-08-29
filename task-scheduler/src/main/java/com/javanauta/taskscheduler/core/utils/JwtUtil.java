package com.javanauta.taskscheduler.core.utils;


import com.javanauta.taskscheduler.infrastructure.exceptions.UserNotLoggedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${task-tide.security.secret-key}")
    private String base64SecretKey;

    private SecretKey getSigningKey() {

        System.out.println(base64SecretKey);
        byte[] keyBytes = Base64.getDecoder().decode(base64SecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUserEmailFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UserNotLoggedException("Please login first");
        }
        return authentication.getName();
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUserEmailFromToken(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
