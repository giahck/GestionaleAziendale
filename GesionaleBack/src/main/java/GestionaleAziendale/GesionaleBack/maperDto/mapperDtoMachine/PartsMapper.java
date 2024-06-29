package GestionaleAziendale.GesionaleBack.maperDto.mapperDtoMachine;

import GestionaleAziendale.GesionaleBack.dto.dtoMachine.PartsDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Parts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PartsMapper {
    PartsDto partsToPartsDto(Parts parts);
    Parts partsDtoToParts(PartsDto partsDto);
}
