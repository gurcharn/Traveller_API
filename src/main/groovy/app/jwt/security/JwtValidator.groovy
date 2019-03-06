package app.jwt.security

import app.jwt.model.JwtUser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component


@Component
class JwtValidator {

    private String secret = "youtube"

    JwtUser validate(String token){
        JwtUser jwtUser = null

        try{
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
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
