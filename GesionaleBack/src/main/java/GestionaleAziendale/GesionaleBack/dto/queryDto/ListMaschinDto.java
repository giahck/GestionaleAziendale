package GestionaleAziendale.GesionaleBack.dto.queryDto;


import GestionaleAziendale.GesionaleBack.entity.utenti.Competenza;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class ListMaschinDto {
    private Integer id;
    private String nomeMacchina;
    private String marca;
    private String description;
    private Competenza competenza;
  /*  public ListMaschinDto(Integer id, String nomeMacchina, String marca, String description) {
        this.id = id;
        this.nomeMacchina = nomeMacchina;
        this.marca = marca;
        this.description = description;
    }*/
}
