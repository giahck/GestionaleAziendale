package GestionaleAziendale.GesionaleBack.entity.machine.genericMachine;

import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SuperBuilder
@ToString
@NoArgsConstructor
@Table(name = "machines_generic")

public class MachineGeneric extends Machine {
    @JsonProperty
    private String description;
    @JsonProperty
    @Column(nullable = false, unique = true)
    private String matricola;
    @JsonProperty
    private String photo;



}
