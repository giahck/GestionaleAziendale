package GestionaleAziendale.GesionaleBack.repository.machineRepository;

import GestionaleAziendale.GesionaleBack.entity.machine.genericMachine.MachineGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineGenericRepository extends JpaRepository<MachineGeneric, Integer>{
}
