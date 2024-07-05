package GestionaleAziendale.GesionaleBack.maperDto.mapperDtoMachine;


import GestionaleAziendale.GesionaleBack.dto.dtoMachine.GenericMachineDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Parts;
import GestionaleAziendale.GesionaleBack.entity.machine.genericMachine.MachineGeneric;
import org.mapstruct.*;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MachineGenericMapper {
    @Mapping(target = "usersId", ignore = true)
    @Mapping(target = "partsId", ignore = true)
    @Mapping(target = "competenzaId", ignore = true)
    GenericMachineDto machineToGenericDto(MachineGeneric machineGeneric);


    @Mapping(target = "parts", ignore = true)
    @Mapping(target = "competenza", ignore = true)
    MachineGeneric genericDtoToMachine(GenericMachineDto genericDto);


}
