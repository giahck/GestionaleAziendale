package GestionaleAziendale.GesionaleBack.dto;

import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CompetenzaDto {
    @NotBlank(message = "Nome Competenza cannot be blank")
    private String nomeCompetenza;

    @NotBlank(message = "Descrizione cannot be blank")
    private String descrizione;

    @NotNull(message = "Id Risorsa cannot be null")
    private Long idRisorsa;

    @Min(value = 1, message = "Livello should not be less than 1")
    @Max(value = 2, message = "Livello should not be greater than 5")
    private int livello;

    @NotNull(message = "Ruoli cannot be null")
    private List<Integer> ruoliId;
}
