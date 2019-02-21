package app.userProfile

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    User getUser(@RequestParam(required = true) String userId){
        return userService.findByUserId(userId);
    }

    @PatchMapping
    void updateUser(@RequestBody User user){
        if(!isUserValid(user) || !user.getUserId())
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "User Validation Failed")
        else if(!isUserExist(user.getEmail()) || !isUserIdExist(user.getUserId()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "User doesn't exist")
        else
            userService.save(user)
    }

    void deleteUser(String userId){
        if(isUserIdExist(userId))
            userService.delete(userId)
        else
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "User Id not found")
    }

    User createUser(User user){
        if(!isUserValid(user))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "User Validation Failed")
        else if(isUserExist(user.getEmail()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST , "User Already Exist")
        else
           return userService.insert(user)
    }

    boolean isUserValid(User user){
        return user && user.getEmail()
    }

    boolean isUserExist(String email){
        return !(userService.findByEmail(email).isEmpty())
    }

    boolean isUserIdExist(String userId){
        return userService.findByUserId(userId)
    }
}
