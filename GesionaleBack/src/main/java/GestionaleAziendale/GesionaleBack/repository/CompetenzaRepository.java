package GestionaleAziendale.GesionaleBack.repository;

import GestionaleAziendale.GesionaleBack.entity.utenti.Competenza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenzaRepository extends JpaRepository<Competenza,Integer> {
}
