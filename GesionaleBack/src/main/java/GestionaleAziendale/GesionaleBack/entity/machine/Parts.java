package GestionaleAziendale.GesionaleBack.entity.machine;

import GestionaleAziendale.GesionaleBack.entity.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parts")
public class Parts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, name = "nome_parte")
    private String nomeParte;
    @Column(nullable = false)
    private String descrizione;
    private String note;
    @ManyToOne
    @JoinColumn(name = "id_machine")
    private Machine machine;
    @OneToMany(mappedBy = "parts")
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "parts")
    private List<Piece> pieces;
    private int quantityParts;

}
