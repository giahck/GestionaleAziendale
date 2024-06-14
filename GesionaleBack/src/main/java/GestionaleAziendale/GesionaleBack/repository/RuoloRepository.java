package GestionaleAziendale.GesionaleBack.repository;


import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import GestionaleAziendale.GesionaleBack.enums.RuoloEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo, Integer> {
    Optional<Ruolo> findByNomeRuolo(RuoloEnum nomeRuolo);
}
