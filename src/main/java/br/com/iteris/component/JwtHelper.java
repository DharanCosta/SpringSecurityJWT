package br.com.iteris.component;

import br.com.iteris.domain.dtos.*;
import br.com.iteris.domain.entities.*;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.nio.charset.*;
import java.security.*;
import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.util.stream.*;

@Component
public class JwtHelper {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private Long expiration;

//  Recebendo o objeto User e criando o JWT
    public String createJwt(User user){
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("name", user.getName())
                .signWith(getSecretKey())
                .setExpiration(toExpirationDate())
                .claim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .compact();
    }

//  A biblioteca do Jwt nos ajuda com a validação do token (private key e data de expiração).
//  Caso o token seja inválido, uma exceção será lançada.
    public AuthenticatedUserDetails validateAndGetUser(String jwtToken){
        Claims claims = Jwts.parserBuilder().setSigningKey(getSecretKey()).build()
                .parseClaimsJws(jwtToken).getBody();
        return new AuthenticatedUserDetails(
                Long.valueOf(claims.getSubject()),
                claims.get("name", String.class),
                claims.get("roles", List.class));
    }

    private Date toExpirationDate(){
        return Date.from(LocalDateTime.now().plus(expiration, ChronoUnit.MINUTES).atZone(ZoneId.systemDefault()).toInstant());
    }

    private Key getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}