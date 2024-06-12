package GestionaleAziendale.GesionaleBack.maperDto;

import GestionaleAziendale.GesionaleBack.dto.UserDto;
import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
         //   @Mapping(target = "dataDiNascita", source = "user.dataDiNascita", qualifiedByName = "dateToLocalDate"),
            @Mapping(target = "ruoloId", source = "user.ruolo", qualifiedByName = "ruoloSetToIdSet"),
            @Mapping(target = "password", ignore = true) // Ignore password when mapping from Users to UserDto
    })
    UserDto userToUserDto(Users user);

    @Mappings({
          //  @Mapping(target = "dataDiNascita", source = "userDto.dataDiNascita", qualifiedByName = "localDateToDate"),
            @Mapping(target = "ruolo", source = "userDto.ruoloId", qualifiedByName = "idSetToRuoloSet"),
            @Mapping(target = "password", ignore = true) // Ignore password when mapping from UserDto to Users
    })
    Users userDtoToUser(UserDto userDto);

   /* @Named("dateToLocalDate")
    default LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }*/

   /* @Named("localDateToDate")
    default Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }*/
@Named("ruoloSetToIdSet")
    default Set<Integer> ruoloSetToIdSet(Set<Ruolo> ruoli) {
        return ruoli.stream().map(Ruolo::getIdRuolo).collect(Collectors.toSet());
    }
@Named("idSetToRuoloSet")
    default Set<Ruolo> idSetToRuoloSet(Set<Integer> ids) {
        return ids.stream().map(id -> {
            Ruolo ruolo = new Ruolo();
            ruolo.setIdRuolo(id);
            return ruolo;
        }).collect(Collectors.toSet());
    }
}
