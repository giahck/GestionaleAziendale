package GestionaleAziendale.GesionaleBack.maperDto;


import GestionaleAziendale.GesionaleBack.dto.RuoloDto;
import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface RuoloMapper {
    RuoloMapper INSTANCE = Mappers.getMapper(RuoloMapper.class);

   // @Mapping(target = "idRuolo", ignore = true)
    Ruolo toEntity(RuoloDto ruoloDto);

    RuoloDto toDto(Ruolo ruolo);
}