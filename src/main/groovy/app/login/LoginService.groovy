package app.login

import org.springframework.data.mongodb.repository.MongoRepository

interface LoginService extends MongoRepository<Login, String> {
}
