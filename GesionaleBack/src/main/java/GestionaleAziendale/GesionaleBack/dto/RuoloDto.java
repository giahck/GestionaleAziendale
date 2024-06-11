package GestionaleAziendale.GesionaleBack.dto;


import GestionaleAziendale.GesionaleBack.enums.RuoloEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RuoloDto {

    @NotNull(message = "Nome Ruolo cannot be null")
    private RuoloEnum nomeRuolo;

    @NotBlank(message = "Descrizione cannot be blank")
    private String descrizione;

    @Min(value = 1, message = "Livello should not be less than 1")
    @Max(value = 5, message = "Livello should not be greater than 5")
    private int livello;


     private List<CompetenzaDto> competenzePredefinite;
}