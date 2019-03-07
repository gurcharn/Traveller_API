package app.jwt.security

import app.jwt.model.JwtUser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtGenerator {

    @Value('${jwt.secretKey}')
    private String secretKey

    String generate (JwtUser jwtUser) {
        Claims claims = Jwts.claims().setSubject(jwtUser.getUserName())

        claims.put("userId", String.valueOf(jwtUser.getId()))

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact()
    }
}