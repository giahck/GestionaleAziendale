package GestionaleAziendale.GesionaleBack.entity.utenti;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "competenze")
public class Competenza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompetenza;
    private String nomeCompetenza;
    private String descrizione;
    private Long idRisorsa; // Foreign Key
    private int livello;

    @ManyToMany(mappedBy = "competenzePredefinita")
    private List<Ruolo> ruoli;
}
