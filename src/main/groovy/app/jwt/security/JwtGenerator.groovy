package app.jwt.security

import app.jwt.model.JwtUser
import app.jwt.model.JwtUserDetails
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtGenerator {

    @Value('${jwt.secretKey}')
    private String secretKey

    JwtUserDetails generate (JwtUser jwtUser) {
        String token
        JwtUserDetails jwtUserDetails
        Claims claims = Jwts.claims().setSubject(jwtUser.getUserName())

        claims.put("userId", String.valueOf(jwtUser.getId()))

        token = Jwts.builder()
                            .setClaims(claims)
                            .signWith(SignatureAlgorithm.HS512, secretKey)
                            .compact()

        jwtUserDetails = new JwtUserDetails(jwtUser.getUserName(), jwtUser.getId(), token, null)

        return jwtUserDetails
    }
}