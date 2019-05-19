package app.login

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author Gurcharn Singh Sikka
 * @version 1.0
 *
 * Interface for Login Service with DB
 */
interface LoginService extends MongoRepository<Login, String> {
    /**
     * Method to find user by id
     * @param userId
     * @return Login
     */
    Login findByUserId(String userId)

    /**
     * Method to find list of users with username
     * @param username
     * @return List<Login>
     */
    List<Login> findByUsername(String username)
}
