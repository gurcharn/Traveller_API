package app.jwt.security

import app.jwt.model.JwtUser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class JwtValidator {

    @Value('${jwt.secretKey}')
    private String secretKey

    JwtUser validate(String token){
        JwtUser jwtUser = null

        try{
            Claims body = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()

            jwtUser = new JwtUser()

            jwtUser.setUserName(body.getSubject())
            jwtUser.setId((String) body.get("userId"))
        } catch(Exception e){
            System.out.println(e)
        }

        return jwtUser
    }

}
