package app.chat

import org.springframework.data.mongodb.repository.MongoRepository

interface ChatService extends MongoRepository<Chat, String> {
    Chat findByChatId(String chatId)
    List<Chat> findByUserOne(String userOne)
    List<Chat> findByUserTwo(String userTwo)
}
