package app.signUp

import app.login.Login
import app.userProfile.User

class SignUp {
    private User user;
    private Login login;

    SignUp() {}

    SignUp(User user, Login login) {
        this.user = user
        this.login = login
    }

    User getUser() {
        return user
    }

    Login getLogin() {
        return login
    }
}
