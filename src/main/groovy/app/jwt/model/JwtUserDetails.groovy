package app.jwt.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtUserDetails implements UserDetails{

    private String userName
    private String token
    private String id
    private Collection<? extends GrantedAuthority> authorities

    JwtUserDetails (String userName, String id, String token, List<GrantedAuthority> grantedAuthorities) {
        this.userName=userName
        this.token=token
        this.id=id
        this.authorities=grantedAuthorities
    }

    String getUserName () {
        return userName
    }

    String getToken () {
        return token
    }

    String getId () {
        return id
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities () {
        return authorities
    }

    @Override
    String getPassword () {
        return null
    }

    @Override
    String getUsername () {
        return userName
    }

    @Override
    boolean isAccountNonExpired () {
        return true
    }

    @Override
    boolean isAccountNonLocked () {
        return true
    }

    @Override
    boolean isCredentialsNonExpired () {
        return true
    }

    @Override
    boolean isEnabled () {
        return true
    }
}
