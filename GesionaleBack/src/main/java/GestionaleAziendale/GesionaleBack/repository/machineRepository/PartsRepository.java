package GestionaleAziendale.GesionaleBack.repository.machineRepository;

import GestionaleAziendale.GesionaleBack.entity.machine.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartsRepository extends JpaRepository<Parts, Integer> {

    List<Parts> findByIdIn(List<Integer> ids);

}
