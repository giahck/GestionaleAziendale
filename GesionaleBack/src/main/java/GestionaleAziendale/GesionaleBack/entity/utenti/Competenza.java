package GestionaleAziendale.GesionaleBack.entity.utenti;



import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    //private Long idRisorsa; // Foreign Key
    private int livello;
    @JsonBackReference
    @ManyToMany(mappedBy = "competenze")
    private Set<Users> usersId= new HashSet<>(); ;

    public void addUser(Users user) {
        this.usersId.add(user);
        user.getCompetenze().add(this);
    }
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "id_machine")
    private Machine machine;

    @Override
    public String toString() {
        return "Competenza{" +
                "idCompetenza=" + idCompetenza +
                ", nomeCompetenza='" + nomeCompetenza + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", livello='" + livello + '\'' +
                ", machine='" + machine + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Competenza)) return false;
        Competenza competenza = (Competenza) o;
        return idCompetenza == competenza.idCompetenza;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompetenza);
    }
}
