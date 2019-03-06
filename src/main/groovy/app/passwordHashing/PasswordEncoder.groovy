package app.passwordHashing

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component

@Component
class PasswordEncoder {
    String encode(String password){
        BCrypt.hashpw(password, BCrypt.gensalt())
    }

    Boolean macth(String password, String hashPassword){
        BCrypt.checkpw(password, hashPassword)
    }
}
