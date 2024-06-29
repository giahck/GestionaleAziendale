package GestionaleAziendale.GesionaleBack.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PieceDtoRequestChat {
    private String nomePezzo;
    private String descrizionePezzo;
    private String nomeParte;
    private String descrizioneParte;
    private String nomeMacchina;
}
