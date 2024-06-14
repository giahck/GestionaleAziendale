package GestionaleAziendale.GesionaleBack.maperDto;

import GestionaleAziendale.GesionaleBack.dto.UserRegDto;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserRegisterMapper {
    UserRegisterMapper INSTANCE = Mappers.getMapper(UserRegisterMapper.class);


    @Mapping(target = "password", ignore = true)
    UserRegDto toDto(Users entity);



    @Mapping(target = "password", ignore = true)
    Users toEntity(UserRegDto dto);
}
