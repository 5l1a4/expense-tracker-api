package com.analistas.expensetrackerapi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    private SecretKey secretKey;

    @PostConstruct
    public void initKey(){
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateToken(String username){

        Long now = new Date().getTime();
        Date expirationDate = new Date(now + jwtExpiration);

        SecretKey secretkey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(secretkey).compact();
    }

    public boolean validateToken(String token){

        try {
             Jwts.parser()
                     .verifyWith(secretKey)
                     .build()
                     .parseSignedClaims(token);

             return true;

        }catch (Exception e){

            return false;
        }

    }

    public String getUsernameFromToken(String token){
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token).getPayload().getSubject();

    }

}
