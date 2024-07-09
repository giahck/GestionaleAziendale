package GestionaleAziendale.GesionaleBack.dto.dtoMachine;

import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.machine.Piece;
import GestionaleAziendale.GesionaleBack.entity.ticket.Ticket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*ok*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartsDto {
    @NotBlank(message = "Nome Parte cannot be blank")
    private String nomeParte;
    @NotBlank(message = "Descrizione cannot be blank")
    private String descrizione;
    private String note;
    @NotNull(message = "Id Machine cannot be null")
    private int quantityParts;
    @NotNull(message = "Id Machine cannot be null")
    private int machineId;

}
