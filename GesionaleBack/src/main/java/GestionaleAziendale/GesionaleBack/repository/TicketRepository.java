package GestionaleAziendale.GesionaleBack.repository;

import GestionaleAziendale.GesionaleBack.entity.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
