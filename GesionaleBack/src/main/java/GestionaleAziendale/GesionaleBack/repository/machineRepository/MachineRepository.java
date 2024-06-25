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

    @Query("SELECT new GestionaleAziendale.GesionaleBack.dto.queryDto.ListMaschinDto(mg.id, mg.nomeMacchina, mg.marca, mg.modello, mg.competenza, mg.description) FROM MachineGeneric mg LEFT JOIN mg.competenza")
    List<ListMaschinDto> findSelectedMachineDetailsForCompetenze();



}
