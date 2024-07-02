package GestionaleAziendale.GesionaleBack.entity.machine;

import GestionaleAziendale.GesionaleBack.entity.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_machine")
    private Machine machine;
    //@JsonIgnore
    @ManyToMany(mappedBy = "parts")
    private List<Ticket> tickets;
    @JsonManagedReference
    @OneToMany(mappedBy = "parts")
    private List<Piece> pieces;
    private int quantityParts;

}
