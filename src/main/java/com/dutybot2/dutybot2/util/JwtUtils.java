package com.dutybot2.dutybot2.util;

import com.dutybot2.dutybot2.details.CadetDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.*;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration lifetime;

    public String genereteToken(CadetDetails cadetDetails){
        Map<String,Object> claims = new HashMap<>();
        List<String> roles = cadetDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put("roles",roles);

        Date issuedDate = new Date();
        Date expiredDate =new Date(issuedDate.getTime() + lifetime.toMillis());

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return Jwts
                .builder()
                .claims(claims)
                .issuedAt(issuedDate)
                .expiration(expiredDate)
                .subject(cadetDetails.getUsername())
                .signWith(key)
                .compact();
    }

    public String getUsername(String token){
        return getAllClaims(token).getSubject();
    }

    public List<String> getRoles(String token){
        return getAllClaims(token).get("roles",List.class);
    }

    private Claims getAllClaims(String token){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
