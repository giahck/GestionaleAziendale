package GestionaleAziendale.GesionaleBack.repository.machineRepository;

import GestionaleAziendale.GesionaleBack.dto.queryDto.ListMaschinDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {
    @Query("SELECT m FROM Machine m")
    List<Machine> findAllMachinesAndSubclasses();

    @Query("SELECT m, g.description FROM Machine m LEFT JOIN MachineGeneric g ON m.id = g.id")
    List<ListMaschinDto> findMachineDetails();

}
