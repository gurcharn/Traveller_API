package app.jwt.security

import app.jwt.model.JwtAuthenticationToken
import app.jwt.model.JwtUser
import app.jwt.model.JwtUserDetails
import app.login.LoginController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component


@Component
class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtValidator validator
    @Autowired
    private LoginController loginController

    @Override
    protected void additionalAuthenticationChecks (UserDetails userDetails, UsernamePasswordAuthenticationToken authenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser (String username, UsernamePasswordAuthenticationToken authenticationToken) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authenticationToken
        String token = jwtAuthenticationToken.getToken()

        JwtUser jwtUser = validator.validate(token)

        if(jwtUser == null)
            throw new RuntimeException("JWT token is incorrect")
        else if(!loginController.isUserIdExist(jwtUser.getId()) && !loginController.isLoginUsernameExist(jwtUser.getUserName()))
            throw new RuntimeException("JWT token is incorrect")

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin")

        JwtUserDetails jwtUserDetails = new JwtUserDetails(jwtUser.getUserName(), jwtUser.getId(), token, grantedAuthorities)

        return jwtUserDetails
    }

    @Override
    boolean supports (Class<?> aClass) {
        return (JwtAuthenticationToken.class.isAssignableFrom(aClass))
    }
}