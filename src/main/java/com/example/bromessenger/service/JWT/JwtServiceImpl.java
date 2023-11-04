package com.example.bromessenger.service.JWT;

import com.example.bromessenger.sex.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    @Override
    public Long extractUserName(String token) {
        //Этот метод извлекает имя пользователя (subject) из токена
        //Возвращает имя пользователя, извлеченное из токена
        final Claims claims = extractAllClaims(token);
        return claims.get("id", Long.class);
    }

    @Override
    public String generateToken(CustomUserDetails userDetails) {
        // Генерирует JWT токен для указанного объекта UserDetails
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDetails.getUserId());
        return generateToken(claims, userDetails);
    }

    @Override
    public boolean isTokenValid(String token, CustomUserDetails userDetails) {
        //Возвращает true, если токен действителен для указанного пользователя, в противном случае - falseВозвращает true
        final Long userId = extractUserName(token);
        return (userId.equals(userDetails.getUserId())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, CustomUserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUserId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        //Извлекает дату истечения срока действия токена из JWT токена
        //Возвращает дату истечения срока действия токена
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        //Возвращает ключ подписи для создания и проверки подписи JWT токенов 3 фаза jwt токена
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
