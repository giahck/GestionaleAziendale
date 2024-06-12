package GestionaleAziendale.GesionaleBack.repository;


import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import GestionaleAziendale.GesionaleBack.enums.RuoloEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuoloRepository extends JpaRepository<Ruolo, Integer> {
    Ruolo findByNomeRuolo(RuoloEnum nomeRuolo);
}
