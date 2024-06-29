
package GestionaleAziendale.GesionaleBack.service.chat;

import GestionaleAziendale.GesionaleBack.dto.chat.ChatGptResponse;
import GestionaleAziendale.GesionaleBack.dto.chat.MessageClient;
import org.springframework.stereotype.Service;

    @Service
    public class ChatGPTService {

        public ChatGptResponse convert(ChatGptResponse chatGptResponse, MessageClient message){

            return chatGptResponse;
        }

/*    System.out.println(chatGptResponse);
        MessageClient messageToSend = new MessageClient();*/
    //   assert chatGptResponse != null;
    //   messageToSend.setContent(chatGptResponse.getChoices().getFirst().getMessage().getContent());
    /*    messageToSend.setContent("ciao");
        messageToSend.setFrom("bot");
        System.out.println(messageToSend);*/

    }


