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
    @SendToUser("/topic/messages")// solo per l'utente che ha inviato il messaggio
    public ChatGptResponse send(@Payload MessageClient message, SimpMessageHeaderAccessor headerAccessor) throws JsonProcessingException {

        ChatGPTRequest request=new ChatGPTRequest(model, message.getContent());
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
        assert chatGptResponse != null;
        chatGptResponse.setFrom("bot");
        System.out.println(chatGptResponse);
        return chatGPTService.convert(chatGptResponse,message);
    }





}
