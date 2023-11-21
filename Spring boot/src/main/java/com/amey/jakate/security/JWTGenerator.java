package com.amey.jakate.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JWTGenerator {
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currenDate = new Date();
        Date expiryDate = new Date(currenDate.getTime() + 1000000000);
      String token = Jwts.builder()
              .setSubject(username)
              .setIssuedAt(currenDate)
              .setExpiration(expiryDate)
              .signWith(SignatureAlgorithm.HS512, "secret")
              .compact();
      return token;
    }
    public String getUsernameFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Boolean validateToken(String token){
try{
Jwts.parser().setSigningKey("secret").parseClaimsJws(token);
return true;
} catch(Exception e){
    throw new AuthenticationCredentialsNotFoundException("Jwt was Expired/Incorrect");
}
    }
}
