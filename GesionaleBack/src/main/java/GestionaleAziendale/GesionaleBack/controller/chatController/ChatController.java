package GestionaleAziendale.GesionaleBack.controller.chatController;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
 /*   @MessageMapping("/chat")
    @SendTo("/topic/messages")// tutti i client connessi a /topic/messages riceveranno il messaggio
    public MessageToSend send(ChatMessage message) {
        MessageToSend messageToSend = new MessageToSend();
        messageToSend.setMessage(message.getMessage());
        messageToSend.setText(message.getText());
        return messageToSend;
    }*/
}
