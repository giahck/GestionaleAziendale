package GestionaleAziendale.GesionaleBack.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String role;
    private String content;//prompt
}