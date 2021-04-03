package com.laundrative_v2.app.utility;

import com.laundrative_v2.app.beans.json.customer.CustomerDetails;
import com.laundrative_v2.app.repository.customerRepo.CustomerRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTUtil
{
    //TODO
    // change SECRET_KEY

    private String SECRET_KEY = "secret";

    public String extractEmail(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractId(String token)
    {
        return Long.valueOf(extractClaim(token, Claims::getId));
    }

    public Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAlClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAlClaims(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(CustomerDetails details)
    {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, details.getId(), details.getEmail());
    }

    private String createToken(Map<String, Object> claims, String id, String subject)
    {
        return Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5)) // 5 hour expiration time
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, CustomerDetails details)
    {
        final String email = extractEmail(token);
        return (email.equals(details.getEmail()) && !isTokenExpired(token));
    }
}

