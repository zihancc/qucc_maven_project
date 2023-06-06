package com.qucc;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class SpringbootWebTliasApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testJwtGen(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 12);
        claims.put("name", "qucc1212121");
        String token = Jwts.builder()
        .signWith(SignatureAlgorithm.HS256, "qucc")
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
        .compact();

        System.out.println(token);
    }

    @Test
    public void parseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("qucc")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoicXVjYzEyMTIxMjEiLCJpZCI6MTIsImV4cCI6MTY4NTcxNDg0OH0.YXUqutBuQDq2l_qNBpPXHyxvfOJu2Zwut4wztCFmgHs")
                .getBody();
        System.out.println(claims);
    }
}
