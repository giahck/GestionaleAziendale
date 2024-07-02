package GestionaleAziendale.GesionaleBack.repository;

import GestionaleAziendale.GesionaleBack.dto.UserDtoCompetenze;
import GestionaleAziendale.GesionaleBack.dto.competenzeAllDto;
import GestionaleAziendale.GesionaleBack.entity.utenti.Competenza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenzaRepository extends JpaRepository<Competenza,Integer> {
    @Query("SELECT new GestionaleAziendale.GesionaleBack.dto.competenzeAllDto(c.idCompetenza, c.nomeCompetenza, c.descrizione, c.livello, m.nomeMacchina, m.modello) " +
            "FROM Competenza c LEFT JOIN c.machine m")
    List<competenzeAllDto> findAllCompetenzeWithMachine();
    @Query("SELECT DISTINCT new GestionaleAziendale.GesionaleBack.dto.UserDtoCompetenze(u.id, u.nome, u.cognome) " +
            "FROM Users u " +
            "JOIN u.competenze c " +
            "WHERE c.id = :idCompetenza")
    List<UserDtoCompetenze> findUsersByCompetenzaId(@Param("idCompetenza") int idCompetenza);

}
