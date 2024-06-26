package GestionaleAziendale.GesionaleBack.dto;

import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class CompetenzeRegDto {
    @NotBlank(message = "Nome Competenza cannot be blank")
    private String nomeCompetenza;

    @NotBlank(message = "Descrizione cannot be blank")
    private String descrizione;

    /*@NotNull(message = "Id Risorsa cannot be null")
    private Long idRisorsa;*/

    @Min(value = 1, message = "Livello should not be less than 1")
    @Max(value = 5, message = "Livello should not be greater than 5")
    private int livello;

   // @NotNull(message = "Ruoli cannot be null")
    private Set<Integer> usersId;

    private int machineId;

}
