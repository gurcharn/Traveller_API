package app.chat

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Chat {

    @Id
    private String chatId
    private String userOne
    private String userTwo
    private List<Message> messages

    Chat() {}

    Chat(String chatId, String userOne, String userTwo, List<Message> messages) {
        this.chatId = chatId
        this.userOne = userOne
        this.userTwo = userTwo
        this.messages = messages
    }

    String getChatId() {
        return chatId
    }

    void setChatId(String chatId) {
        this.chatId = chatId
    }

    String getUserOne() {
        return userOne
    }

    void setUserOne(String userOne) {
        this.userOne = userOne
    }

    String getUserTwo() {
        return userTwo
    }

    void setUserTwo(String userTwo) {
        this.userTwo = userTwo
    }

    List<Message> getMessages() {
        return messages
    }

    void setMessages(List<Message> messages) {
        this.messages = messages
    }

    @Override
    boolean equals (Object o) {
        if(this==null && o==null) return true
        if(this!=null && o==null) return false
        if(this==null && o!=null) return false
        if(!(o instanceof Chat)) return false
        Chat chat=(Chat) o
        return (this.getChatId()==chat.getChatId() && this.getUserOne()==chat.getUserOne() && this.getUserTwo()==chat.getUserTwo())
    }

    /**
     * Method to get hash of object
     * @return int
     */
    int hashCode() {
        int result
        result = (chatId != null ? chatId.hashCode() : 0)
        result = 31 * result + (userOne != null ? userOne.hashCode() : 0)
        result = 31 * result + (userTwo != null ? userTwo.hashCode() : 0)
        return result
    }
}
