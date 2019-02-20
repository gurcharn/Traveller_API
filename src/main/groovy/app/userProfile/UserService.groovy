package app.userProfile

import org.springframework.data.mongodb.repository.MongoRepository

import javax.jws.soap.SOAPBinding

interface UserService extends MongoRepository<User, String> {

    User findByUserId(String userId);
    List<User> findByEmail(String email);
}
