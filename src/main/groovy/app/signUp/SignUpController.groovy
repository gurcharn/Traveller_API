package app.signUp

import app.login.Login
import app.login.LoginController
import app.login.LoginService
import app.userProfile.User
import app.userProfile.UserController
import app.userProfile.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException

@RestController
@RequestMapping("/signUp")
class SignUpController {

    @Autowired
    private LoginController loginController
    @Autowired
    private UserController userController

    @PostMapping
    void signUp(@RequestBody SignUp signUp){
        Login login = loginController.createLogin(signUp.getLogin())
        User user = signUp.getUser()

        user.setUserId(login.getUserId())
        userController.createUser(user)
    }

    @DeleteMapping
    void delete(@RequestParam(required = true) String userId){
        userController.deleteUser(userId)
        loginController.deleteLogin(userId)
    }
}
