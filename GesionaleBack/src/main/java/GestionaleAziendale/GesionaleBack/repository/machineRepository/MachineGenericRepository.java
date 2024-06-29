package GestionaleAziendale.GesionaleBack.repository.machineRepository;

import GestionaleAziendale.GesionaleBack.dto.queryDto.ListMaschinDto;
import GestionaleAziendale.GesionaleBack.entity.machine.genericMachine.MachineGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineGenericRepository extends JpaRepository<MachineGeneric, Integer>{
    @Query(value = "SELECT new GestionaleAziendale.GesionaleBack.dto.queryDto.ListMaschinDto(m.id, m.nome_macchina, m.marca, m.description, m.competenza) FROM machines_generic m LEFT JOIN machines_generic g ON m.id = g.id", nativeQuery = true)
    List<ListMaschinDto> findMachineDetails();
    /*@Query(value = "SELECT m.id, m.nome_macchina, m.marca, m.description, m.competenza FROM machines_generic m", nativeQuery = true)
    List<Object[]> findMachineDetails();*/
}
