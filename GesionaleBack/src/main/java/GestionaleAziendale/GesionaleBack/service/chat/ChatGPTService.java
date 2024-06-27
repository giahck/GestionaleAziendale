package GestionaleAziendale.GesionaleBack.service.chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;



    @Service
    public class ChatGPTService {

        @Value("${openai.api.key}")
        private String apiKey;

        private final RestTemplate restTemplate;

        public ChatGPTService(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        public String getResponse(String prompt) {
            String url = "https://api.openai.com/v1/engines/davinci-codex/completions";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("Content-Type", "application/json");

            String requestBody = "{\"prompt\":\"" + prompt + "\",\"max_tokens\":150}";
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            return response.getBody();
        }
    }

