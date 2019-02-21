package app.login

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
}
