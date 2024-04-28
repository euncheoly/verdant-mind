package com.zeroinon.chatterboard.service;


import com.zeroinon.chatterboard.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;


@Service
public class JwtService {


    @Value("${JWT_SECRET}")
    public String jwtSecretKey; //TO BE REPLACED USING KEY VAULT


    public enum TokenRole {
        ACCESS,
        REFRESH;
    }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDTO userDTO) {
        String userId =  extractUserId(token);
        return userId.equals(userDTO.getUserId()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public<T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }



    public String generateToken(String userId, TokenRole tokenRole) {

        int duration;
        if(tokenRole == TokenRole.ACCESS) {
            duration = 1000 * 60 * 60;
        }else{
            duration = 1000 * 60 * 60 * 24;
        }

        String token = Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + duration))
                .signWith(getSigningKey())
                .compact();
        return token;
    }

    private SecretKey getSigningKey() {
        System.out.println("jwtSecretKey: " + jwtSecretKey );
        byte[] keyBytes = Decoders.BASE64URL.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);

    }




}
