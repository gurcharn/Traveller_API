package app.chat

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/secure/chat")
class ChatController {

    @Autowired
    private ChatService chatService

    @GetMapping("/chatId")
    Chat getChat(@RequestParam(required = true) String chatId){
        Chat chat = chatService.findByChatId(chatId)
        if(chat == null || chat.getChatId() == null)
            chat = new Chat()

        return chat
    }

    @GetMapping("/userId")
    Set<Chat> getChatByUserId(@RequestParam(required = true) String userId){
        Set<Chat> chatSet = new HashSet<Chat>()
        chatSet.addAll(chatService.findByUserOne(userId))
        chatSet.addAll(chatService.findByUserTwo(userId))

        if(chatSet == null || chatSet.isEmpty())
            chatSet.add(new Chat())

        return chatSet
    }

    @PostMapping
    Chat insertChat(@RequestBody Chat chat){
        if(chat.getChatId())
            chatService.save(chat)

        Chat findChat = findChat(chat.getUserOne(), chat.getUserTwo())

        if(findChat == null)
            chatService.insert(chat)
        else{
            if(chat.getMessages().isEmpty())
                return findChat
            else{
                findChat.setMessages(chat.getMessages())
                chatService.save(findChat)
            }
        }
    }

    @PatchMapping
    Chat updateChat(@RequestBody Chat chat){
        return chatService.save(chat)
    }

    Chat findChat(String userOne, userTwo){
        Set<Chat> chatSet = new HashSet<Chat>()
        chatSet.addAll(chatService.findByUserOne(userOne))
        chatSet.addAll(chatService.findByUserTwo(userOne))

        if(chatSet==null || chatSet.isEmpty())
            return null
        else{
            for (Chat chat : chatSet){
                if(chat.getUserOne()==userOne && chat.getUserTwo()==userTwo)
                    return chat
                else if(chat.getUserOne()==userTwo && chat.getUserTwo()==userOne)
                    return chat
            }
        }

        return null
    }
}
