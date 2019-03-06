package app.jwt.security

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtSuccessHandler implements AuthenticationSuccessHandler{
    @Override
    void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("Successfully Authenticated")
    }
}
