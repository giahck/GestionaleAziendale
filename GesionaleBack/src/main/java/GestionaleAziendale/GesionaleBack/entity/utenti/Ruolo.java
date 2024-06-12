package GestionaleAziendale.GesionaleBack.entity.utenti;


import GestionaleAziendale.GesionaleBack.enums.RuoloEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ruoli")
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRuolo;
    @Enumerated(EnumType.STRING)
    private RuoloEnum nomeRuolo;
    private String descrizione;
    private int livello;
}
