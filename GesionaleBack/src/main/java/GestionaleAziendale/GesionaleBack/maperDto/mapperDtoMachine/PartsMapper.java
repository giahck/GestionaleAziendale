package GestionaleAziendale.GesionaleBack.maperDto.mapperDtoMachine;

import GestionaleAziendale.GesionaleBack.dto.dtoMachine.PartsDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Parts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PartsMapper {
    @Mapping(target = "machine", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "pieces", ignore = true)
    PartsDto partsToPartsDto(Parts parts);

    @Mapping(target = "machine", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "pieces", ignore = true)
    Parts partsDtoToParts(PartsDto partsDto);
}
