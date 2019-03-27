package app.login

import app.jwt.model.JwtUser
import app.jwt.model.JwtUserDetails
import app.jwt.security.JwtGenerator
import app.passwordHashing.PasswordEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException

@RestController
@RequestMapping("/login")
class LoginController {

    @Autowired
    private LoginService loginService
    @Autowired
    private JwtGenerator jwtGenerator
    @Autowired
    private PasswordEncoder passwordEncoder

    @PostMapping
    JwtUserDetails login(@RequestBody Login login){
        List<Login> loginFromDb = loginService.findByUsername(login.getUsername())

        if(loginFromDb.isEmpty())
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Invalid User")
        else
            authenticateLogin(login, loginFromDb.get(0))
    }

    Login createLogin(Login login){
        if(!isLoginValid(login))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Login Validation Failed")
        else if(isLoginUsernameExist(login.getUsername()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Username already exist")
        else {
            login.setPassword(passwordEncoder.encode(login.getPassword()))
            loginService.insert(login)
        }
    }

    void updateLogin(Login login){
        if(isUserIdExist(login.getUserId()))
            loginService.save(login)
        else
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "User Id not found")
    }

    void deleteLogin(String userId){
        if(isUserIdExist(userId))
            loginService.delete(userId)
        else
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "User Id not found")
    }

    boolean isLoginValid(Login login){
        return login && login.getUsername() && login.getPassword()
    }

    boolean isLoginUsernameExist(String username){
        return !(loginService.findByUsername(username).isEmpty())
    }

    boolean isUserIdExist(String userId){
        return loginService.findByUserId(userId)
    }

    JwtUserDetails authenticateLogin(Login requestBody, Login remote){
        if(requestBody.equals(remote)){
            JwtUser jwtUser = new JwtUser()
            jwtUser.setId(remote.getUserId())
            jwtUser.setUserName(remote.getUsername())

            return jwtGenerator.generate(jwtUser)
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Authentication Failed")
        }
    }
}
