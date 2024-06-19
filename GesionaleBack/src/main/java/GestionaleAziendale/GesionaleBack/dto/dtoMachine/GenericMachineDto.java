package GestionaleAziendale.GesionaleBack.dto.dtoMachine;

import GestionaleAziendale.GesionaleBack.enums.StatoMaschineEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericMachineDto {
    @NotBlank(message = "Nome Macchina cannot be blank")
    private String nomeMacchina;
    private String marca;
    private String modello;
    @NotBlank(message = "Matricola cannot be blank")
    private String matricola;
    @NotNull(message = "Data Acquisto cannot be null")
    private LocalDate dataAcquisto;
    @NotNull(message = "Stato Macchina cannot be null")
    private StatoMaschineEnum statoMaschine;
    @NotBlank(message = "Description cannot be blank")
    private String description;


    private List<Integer> partsId;
    private int competenzaId;


}
