
package GestionaleAziendale.GesionaleBack.maperDto;

import GestionaleAziendale.GesionaleBack.dto.CompetenzeRegDto;
import GestionaleAziendale.GesionaleBack.entity.utenti.Competenza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompetenzaMapper {

    CompetenzaMapper INSTANCE = Mappers.getMapper(CompetenzaMapper.class);

    @Mapping(target = "usersId", ignore = true)
    @Mapping(target = "machineId", ignore = true)
    CompetenzeRegDto toDto(Competenza entity);
    @Mapping(target = "usersId", ignore = true)
    @Mapping(target = "machine", ignore = true)
    Competenza toEntity(CompetenzeRegDto dto);
}
