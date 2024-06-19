package GestionaleAziendale.GesionaleBack.repository.machineRepository;

import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {

}
