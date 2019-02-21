package app.login

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
    private LoginService loginService;

    @PostMapping
    String login(@RequestBody Login login){
        //To-DO check for user and password & return token\

        return ""
    }

    @PatchMapping
    void updateLogin(@RequestBody(required = true) Login login){
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

    Login createLogin(Login login){
        if(!isLoginValid(login))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Login Validation Failed")
        else if(isLoginUsernameExist(login.getUsername()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "Username already exist")
        else
            return loginService.insert(login)
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
}
