package GestionaleAziendale.GesionaleBack.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserMachineDto {

    private int userId;


    private List<MachineDto> machines;

    @Data
    @NoArgsConstructor
    public static class MachineDto {

        private int machineId;
        private String nomeMacchina;
        private String marca;
        private String description;
        private String photo;
        private String statoMacchina;
        private List<PartDto> parts;

        @Data
        @NoArgsConstructor
        public static class PartDto {

            private int partId;
            private String nomeParte;
            private String descrizione;
            private String note;
            private int quantityParts;
            private List<PieceDto> pieces;

            @Data
            @NoArgsConstructor
            public static class PieceDto {

                private int pieceId;
                private String nomePezzo;
                private int quantityPiece;
                private String descrizione;
            }
        }
    }
}