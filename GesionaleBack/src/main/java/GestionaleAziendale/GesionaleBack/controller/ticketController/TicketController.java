package GestionaleAziendale.GesionaleBack.controller.ticketController;

import GestionaleAziendale.GesionaleBack.dto.tiket.TicketAddDto;
import GestionaleAziendale.GesionaleBack.entity.ticket.Ticket;
import GestionaleAziendale.GesionaleBack.service.tiketService.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @PostMapping("/add")
    public Ticket addTicket(@RequestBody TicketAddDto ticketAddDto, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new RuntimeException("Richiesta non valida: " + validation.getAllErrors().stream().map(e -> e.getDefaultMessage()).reduce("", (s1, s2) -> s1 + "\n" + s2));
        }

        return ticketService.addTicket(ticketAddDto);
    }
}
