package GestionaleAziendale.GesionaleBack.dto.tiket;

import GestionaleAziendale.GesionaleBack.enums.PriorityTicketEnum;
import GestionaleAziendale.GesionaleBack.enums.StatoTicketEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TicketAddDto {
    private String descrizione;
    private String note;
    private PriorityTicketEnum priority;
    private StatoTicketEnum stato;
    private LocalDateTime dataApertura;
    private LocalDateTime dataChiusura;
    private LocalDateTime dataScadenza;
    private LocalDateTime dataRisposta;
    private LocalDateTime dataUltimaModifica;
    private List<Integer> partIds;
    private int machineId;
    private int userId;
}
