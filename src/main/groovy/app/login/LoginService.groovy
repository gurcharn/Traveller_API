package app.login

import org.springframework.data.mongodb.repository.MongoRepository

interface LoginService extends MongoRepository<Login, String> {

    Login findByUserId(String userId)
    List<Login> findByUsername(String username)
    List<Login> findByEmail(String email)
}
