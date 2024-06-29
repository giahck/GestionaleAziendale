package GestionaleAziendale.GesionaleBack.dto.chat;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MessageClient {
    private String from;
    private String content;
}
