package GestionaleAziendale.GesionaleBack.controller.chatController;

import GestionaleAziendale.GesionaleBack.dto.chat.ChatGPTRequest;
import GestionaleAziendale.GesionaleBack.dto.chat.ChatGptResponse;

import GestionaleAziendale.GesionaleBack.dto.chat.Message;
import GestionaleAziendale.GesionaleBack.dto.chat.MessageClient;


import GestionaleAziendale.GesionaleBack.service.chat.ChatGPTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;


    @Autowired
    private ChatGPTService chatGPTService;
    @MessageMapping("/greet")
    @SendToUser("/topic/messages")// tutti i client connessi a /topic/messages riceveranno il messaggio
    public ChatGptResponse send(@Payload MessageClient message, SimpMessageHeaderAccessor headerAccessor) throws JsonProcessingException {

        ChatGPTRequest request=new ChatGPTRequest(model, message.getContent());
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
        assert chatGptResponse != null;
        chatGptResponse.setFrom("bot");
        System.out.println(chatGptResponse);
//        Message botMessage1 = new Message("assistant", "Hello! How can I assist you today?");
//        ChatGptResponse.Choice choice1 = new ChatGptResponse.Choice(0, botMessage1);
//
//        Message botMessage2 = new Message("assistant", "Is there anything else you need?");
//        ChatGptResponse.Choice choice2 = new ChatGptResponse.Choice(1, botMessage2);
//
//        List<ChatGptResponse.Choice> choices = new ArrayList<>();
//        choices.add(choice1);
//        choices.add(choice2);

      //  return new ChatGptResponse("bot", choices);
        return chatGPTService.convert(chatGptResponse,message);
    }





}
