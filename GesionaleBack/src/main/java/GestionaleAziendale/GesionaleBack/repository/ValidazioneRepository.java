package GestionaleAziendale.GesionaleBack.repository;

import GestionaleAziendale.GesionaleBack.entity.utenti.ValidazioneMail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidazioneRepository extends JpaRepository<ValidazioneMail, Long> {
    ValidazioneMail findByToken(String token);
}
