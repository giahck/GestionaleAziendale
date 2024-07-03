package GestionaleAziendale.GesionaleBack.maperDto.ticket;

import GestionaleAziendale.GesionaleBack.dto.tiket.TicketAddDto;
import GestionaleAziendale.GesionaleBack.entity.ticket.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parts", ignore = true)
    @Mapping(target = "user", ignore = true)
    Ticket toEntity(TicketAddDto dto);
}
