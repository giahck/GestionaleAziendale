package GestionaleAziendale.GesionaleBack.dto.queryDto;

import GestionaleAziendale.GesionaleBack.enums.StatoMaschineEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MachineGenericStatusDto {
    private String description;
    private String matricola;
    private String photo;
    private String nomeMacchina;
    private String marca;
    private String modello;
    private StatoMaschineEnum statoMaschine;
   /* public MachineGenericStatusDto(String description, String matricola, String photo, String nomeMacchina, String marca, String modello, StatoMaschineEnum statoMaschine) {
        this.description = description;
        this.matricola = matricola;
        this.photo = photo;
        this.nomeMacchina = nomeMacchina;
        this.marca = marca;
        this.modello = modello;
        this.statoMaschine = statoMaschine;
    }*/
}
