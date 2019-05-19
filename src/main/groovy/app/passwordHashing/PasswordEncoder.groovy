package app.passwordHashing

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component

/**
 * @author Gurcharn Singh Sikka
 * @version 1.0
 *
 * Class to handle passwor hashing
 */
@Component
class PasswordEncoder {

    /**
     * Method to hash password
     * @param password
     * @return String
     */
    String encode(String password){
        BCrypt.hashpw(password, BCrypt.gensalt())
    }

    /**
     * Method to comapre hashed password with text password
     * @param password
     * @param hashPassword
     * @return boolean
     */
    boolean macth(String password, String hashPassword){
        BCrypt.checkpw(password, hashPassword)
    }
}
