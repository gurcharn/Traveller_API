package app.login

import app.passwordHashing.PasswordEncoder
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Gurcharn Singh Sikka
 * @version 1.0
 *
 * Object to handle login information
 */
@Document
class Login {

    @Id
    private String userId
    private String username
    private String password

    /**
     * Constructor
     */
    Login(){}

    /**
     * Constructor
     * @param username
     * @param password
     */
    Login(String username,String password) {
        this.username = username
        this.password = password
    }

    /**
     * Method to retrieve userid
     * @return String
     */
    String getUserId() {
        return userId
    }

    /**
     * Method to retrieve username
     * @return
     */
    String getUsername() {
        return username
    }

    /**
     * Method to set username
     * @param username
     */
    void setUsername(String username) {
        this.username = username
    }

    /**
     * Method to retrieve password
     * @return String
     */
    String getPassword() {
        return password
    }

    /**
     * Method to set password
     * @param password
     */
    void setPassword(String password) {
        this.password = password
    }

    /**
     * Method to check if two Login objects are equal or not
     * @param o
     * @return boolean
     */
    boolean equals(o) {
        if (!(o instanceof Login)) return false

        Login login = (Login) o

        if (userId == login.userId) return true
        if (!comparePassword(password, login.getPassword())) return false
        if (username != login.username) return false

        return true
    }

    /**
     * Method to get hash of object
     * @return int
     */
    int hashCode() {
        int result
        result = (userId != null ? userId.hashCode() : 0)
        result = 31 * result + (username != null ? username.hashCode() : 0)
        result = 31 * result + (password != null ? password.hashCode() : 0)
        return result
    }

    /**
     * Method to compare a text password with hashed password
     * @param password
     * @param hashPassword
     * @return boolean
     */
    private boolean comparePassword(String password, String hashPassword){
        PasswordEncoder passwordEncoder = new PasswordEncoder()
        passwordEncoder.macth(password, hashPassword)
    }
}
