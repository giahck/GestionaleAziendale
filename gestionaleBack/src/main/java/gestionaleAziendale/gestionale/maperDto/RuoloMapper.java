package gestionaleAziendale.gestionale.maperDto;

import gestionaleAziendale.gestionale.dto.RuoloDto;
import gestionaleAziendale.gestionale.entities.utenti.Ruolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public interface RuoloMapper<Entity, Dto> {
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);
}
