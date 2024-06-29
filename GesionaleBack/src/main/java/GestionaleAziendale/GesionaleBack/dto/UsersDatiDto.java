package GestionaleAziendale.GesionaleBack.dto;

import GestionaleAziendale.GesionaleBack.entity.ticket.Ticket;
import GestionaleAziendale.GesionaleBack.entity.utenti.Competenza;
import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class UsersDatiDto {
    private int id;
    private String email;
    private String cognome;
    private String nome;
    private String sesso;
    private LocalDate dataDiNascita;
    private String comuneDiNascita;
    private String codiceFiscale;
    private Long telefono;
    private String indirizzo;
    private Set<Ruolo> ruolo;
   // private List<Ticket> tickets;
    private Set<Competenza> competenze;
}
