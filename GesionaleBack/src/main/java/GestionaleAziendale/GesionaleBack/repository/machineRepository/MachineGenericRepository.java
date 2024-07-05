package GestionaleAziendale.GesionaleBack.repository.machineRepository;

import GestionaleAziendale.GesionaleBack.dto.queryDto.ListMaschinDto;
import GestionaleAziendale.GesionaleBack.dto.queryDto.MachineGenericStatusDto;
import GestionaleAziendale.GesionaleBack.entity.machine.genericMachine.MachineGeneric;
import GestionaleAziendale.GesionaleBack.entity.utenti.Competenza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineGenericRepository extends JpaRepository<MachineGeneric, Integer>{
    @Query(value = "SELECT new GestionaleAziendale.GesionaleBack.dto.queryDto.ListMaschinDto(m.id, m.nome_macchina, m.marca, m.description, m.competenza) FROM machines_generic m LEFT JOIN machines_generic g ON m.id = g.id", nativeQuery = true)
    List<ListMaschinDto> findMachineDetails();
    @Query("SELECT new GestionaleAziendale.GesionaleBack.dto.queryDto.MachineGenericStatusDto(m.description, m.matricola, m.photo, m.nomeMacchina, m.marca, m.modello, m.statoMaschine) FROM MachineGeneric m")
    List<MachineGenericStatusDto> findMachineStatus();
    @Query("SELECT new GestionaleAziendale.GesionaleBack.dto.queryDto.MachineGenericStatusDto(m.description, m.matricola, m.photo, m.nomeMacchina, m.marca, m.modello, m.statoMaschine) FROM MachineGeneric m WHERE m.competenza IN :competenze")
    List<MachineGenericStatusDto> findMachineStatusByUserCompetenze(@Param("competenze") List<Competenza> competenze);

}
