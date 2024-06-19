package GestionaleAziendale.GesionaleBack.entity.machine.genericMachine;

import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "machines_generic")
public class MachineGeneric extends Machine {
    private String description;
    @Column(nullable = false, unique = true)
    private String matricola;



}
