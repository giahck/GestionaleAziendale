package GestionaleAziendale.GesionaleBack.repository;



import GestionaleAziendale.GesionaleBack.dto.queryDto.ListMaschinDto;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
    @Query("SELECT new GestionaleAziendale.GesionaleBack.dto.queryDto.ListMaschinDto$UserNameDto(u.id,u.nome, u.cognome) " +
            "FROM Users u JOIN u.competenze c WHERE c.id = :competenzaId")
    List<ListMaschinDto.UserNameDto> findUserNamesByCompetenzaId(@Param("competenzaId") Integer competenzaId);

}