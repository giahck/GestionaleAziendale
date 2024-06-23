package GestionaleAziendale.GesionaleBack.maperDto.users;

import GestionaleAziendale.GesionaleBack.dto.UsersDatiDto;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersDatiMapper {

    UsersDatiDto usersToUsersDatiDto(Users users);
    @Mapping(source = "id", target = "id")
    @Mapping(target = "tickets", ignore = true)
    List<UsersDatiDto> usersToUsersDatiDto(List<Users> users);
}
