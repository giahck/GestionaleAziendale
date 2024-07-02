package GestionaleAziendale.GesionaleBack.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
public class competenzeAllDto {
    private int idCompetenza;
    private String nomeCompetenza;
    private String descrizione;
    private int livello;
    private MachineDTO machine;
    private List<UserDtoCompetenze> users;
    public competenzeAllDto(int idCompetenza, String nomeCompetenza, String descrizione, int livello, String nomeMacchina, String modello) {
        this.idCompetenza = idCompetenza;
        this.nomeCompetenza = nomeCompetenza;
        this.descrizione = descrizione;
        this.livello = livello;
        this.machine = new MachineDTO(nomeMacchina, modello);
    }
    @Data
    public static class MachineDTO {
        private String nomeMacchina;
        private String modello;
        public MachineDTO(String nomeMacchina, String modello) {
            this.nomeMacchina = nomeMacchina;
            this.modello = modello;
        }
    }

}