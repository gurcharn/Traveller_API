package app.login

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    String Login(@RequestBody Login login){
        //To-DO check for user and password & return token\

        return "";
    }
}
