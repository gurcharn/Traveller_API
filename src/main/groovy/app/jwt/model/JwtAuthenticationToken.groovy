package app.jwt.model

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken{

    private String token

    JwtAuthenticationToken (String token) {
        super(null, null)
        this.token = token
    }

    String getToken () {
        return token
    }

    void setToken (String token) {
        this.token=token
    }

    @Override
    Object getCredentials () {
        return null
    }

    @Override
    Object getPrincipal () {
        return null
    }
}
