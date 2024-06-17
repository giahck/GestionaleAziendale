package GestionaleAziendale.GesionaleBack.entity.machine;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pieces")
public abstract class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomePezzo;
    private String descrizione;
    private Long seriale;
    private String materiale;
    private int quantityPiece;
    @ManyToOne
    @JoinColumn(name = "id_parts")
    private Parts parts;

}
