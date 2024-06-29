package GestionaleAziendale.GesionaleBack.repository.machineRepository;

import GestionaleAziendale.GesionaleBack.dto.chat.PieceDtoRequestChat;
import GestionaleAziendale.GesionaleBack.entity.machine.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Integer> {

}
