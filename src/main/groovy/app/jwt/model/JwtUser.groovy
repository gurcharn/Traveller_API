package app.jwt.model

class JwtUser {
    private String id
    private String userName

    String getId () {
        return id
    }

    void setId (String id) {
        this.id=id
    }

    String getUserName () {
        return userName
    }

    void setUserName (String userName) {
        this.userName=userName
    }
}
