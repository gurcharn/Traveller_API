package app.login

import app.passwordHashing.PasswordEncoder
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Login {

    @Id
    private String userId
    private String username
    private String password

    Login(){}

    Login(String username,String password) {
        this.username = username
        this.password = password
    }

    String getUserId() {
        return userId
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    boolean equals(o) {
        if (!(o instanceof Login)) return false

        Login login = (Login) o

        if (userId == login.userId) return true
        if (!comparePassword(password, login.getPassword())) return false
        if (username != login.username) return false

        return true
    }

    int hashCode() {
        int result
        result = (userId != null ? userId.hashCode() : 0)
        result = 31 * result + (username != null ? username.hashCode() : 0)
        result = 31 * result + (password != null ? password.hashCode() : 0)
        return result
    }

    private Boolean comparePassword(String password, String hashPassword){
        PasswordEncoder passwordEncoder = new PasswordEncoder()
        passwordEncoder.macth(password, hashPassword)
    }
}
