package GestionaleAziendale.GesionaleBack.dto.queryDto;


import GestionaleAziendale.GesionaleBack.entity.utenti.Competenza;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
/*@NoArgsConstructor*/
@AllArgsConstructor
public class ListMaschinDto {
    private int id;
    private String nomeMacchina;
    private String marca;
    private String modello;
    private Competenza competenza;
    private String description;
    private List<UserNameDto> utenti = new ArrayList<>();
    public ListMaschinDto(int id, String nomeMacchina, String marca, String modello, Competenza competenza, String description) {
        this.id = id;
        this.nomeMacchina = nomeMacchina;
        this.marca = marca;
        this.modello = modello;
        this.competenza = competenza;
        this.description = description;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserNameDto {
        private int id;
        private String nome;
        private String cognome;
    }
}
