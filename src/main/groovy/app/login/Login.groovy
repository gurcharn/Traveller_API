package app.login

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Login {

    @Id
    private String username;
    private String password;

    Login(){}

    Login(String username, String password) {
        this.username = username
        this.password = password
    }
}
