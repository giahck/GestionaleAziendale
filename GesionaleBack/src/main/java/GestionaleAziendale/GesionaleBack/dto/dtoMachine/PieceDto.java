package GestionaleAziendale.GesionaleBack.dto.dtoMachine;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PieceDto {
    @NotNull(message = "Nome Pezzo cannot be null")
    private String nomePezzo;
    @NotNull(message = "Descrizione cannot be null")
    private String descrizione;
    private Long seriale;
    private String materiale;
    @NotNull(message = "Quantity Piece cannot be null")
    private int quantityPiece;
    @NotNull(message = "Id Parts cannot be null")
    private int partsId;
}
