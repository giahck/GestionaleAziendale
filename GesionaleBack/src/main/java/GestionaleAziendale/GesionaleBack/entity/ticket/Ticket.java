package GestionaleAziendale.GesionaleBack.entity.ticket;

import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.machine.Parts;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import GestionaleAziendale.GesionaleBack.enums.PriorityTicketEnum;
import GestionaleAziendale.GesionaleBack.enums.StatoTicketEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descrizione;
    private String note;
    @Enumerated(EnumType.STRING)
    private PriorityTicketEnum priority;
    @Enumerated(EnumType.STRING)
    private StatoTicketEnum stato;
    private LocalDateTime dataApertura;
    private LocalDateTime dataChiusura;
    private LocalDateTime dataScadenza;
    private LocalDateTime dataRisposta;
    private LocalDateTime dataUltimaModifica;

    @ManyToMany
    @JoinTable(
            name = "ticket_part",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private List<Parts> parts;


    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users user;

}
