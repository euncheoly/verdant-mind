package com.zeroinon.chatterboard.service;


import com.zeroinon.chatterboard.exception.GeneralException;
import com.zeroinon.chatterboard.exception.UserException;
import com.zeroinon.chatterboard.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;


@Slf4j
@Service
public class JwtService {


    @Value("${JWT_SECRET}")
    public String jwtSecretKey; //TO BE REPLACED USING KEY VAULT

    private final UserMapper userMapper;

    public JwtService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public enum TokenRole {
        ACCESS,
        REFRESH;
    }

    public enum AccountRole {
        USER,
        ADMIN;
    }


    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValidEveryoneToken(String token) {
        try{
            extractUserId(token);
            return  !isTokenExpired(token);
        }catch (SignatureException s){
            log.error("Unauthorized access token", s);
            return false;
        } catch (ExpiredJwtException e){
            return false;
        }
    }

    public boolean isValidAdminToken(String token) {
        try{
            Claims claims = extractAllClaims(token);
            claims.getSubject();
            claims.getExpiration().after(new Date());
            boolean isAuthorized = String.valueOf(claims.get("usr")).equals(AccountRole.ADMIN.toString());

            return  isAuthorized;
        }catch (SignatureException s){
            log.error("Unauthorized access token", s);
            return false;
        } catch (ExpiredJwtException e){
            return false;
        }
    }


    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public TokenRole extractTokenRole(String token) {
        Claims claims = extractAllClaims(token);
        String role = String.valueOf(claims.get("role"));
        return TokenRole.valueOf(role);
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



    public String generateToken(String userId, TokenRole tokenRole, AccountRole accountRole) {

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
                .claim("role", tokenRole)
                .claim("usr", accountRole)
                .signWith(getSigningKey())
                .compact();
        return token;
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);

    }


    public String reissueToken(String token) {
        if(!isValidEveryoneToken(token)){
            throw new GeneralException.InvalidToken("Invalid token");
        }
        String userId = extractUserId(token);
        AccountRole accountRole = userMapper.isAdminPrivileged(userId) == 1 ? AccountRole.ADMIN : AccountRole.USER;
        return generateToken(userId, TokenRole.ACCESS, accountRole);
    }




}
