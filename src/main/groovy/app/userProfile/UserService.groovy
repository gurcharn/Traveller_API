package app.userProfile

import org.springframework.data.mongodb.repository.MongoRepository

interface UserService extends MongoRepository<User, String> {

    User findByUserId(String userId)
    List<User> findByEmail(String email)
}
