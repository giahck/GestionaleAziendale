package GestionaleAziendale.GesionaleBack.entity.machine;

import GestionaleAziendale.GesionaleBack.entity.machine.genericMachine.MachineGeneric;
import GestionaleAziendale.GesionaleBack.entity.ticket.Ticket;
import GestionaleAziendale.GesionaleBack.entity.utenti.Competenza;
import GestionaleAziendale.GesionaleBack.enums.StatoMaschineEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "machines")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,name = "nome_macchina")
    private String nomeMacchina;
    private String marca;
    private String modello;
    @Column(nullable = false,name = "data_acquisto")
    private LocalDate dataAcquisto;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "stato_macchina")
    private StatoMaschineEnum statoMaschine;

    /*@JsonIgnore(value = true)*/
    @JsonManagedReference
    @OneToMany(mappedBy = "machine")
    private List<Parts> parts;


    @OneToOne(mappedBy = "machine")
    private Competenza competenza;



}
