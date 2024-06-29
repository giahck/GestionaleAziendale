package GestionaleAziendale.GesionaleBack.controller;

import GestionaleAziendale.GesionaleBack.dto.UserMachineDto;
import GestionaleAziendale.GesionaleBack.dto.UsersDatiDto;
import GestionaleAziendale.GesionaleBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;
    @GetMapping()
    @PreAuthorize("hasAuthority('MANAGER') ")
    public List<UsersDatiDto> getAllUser(){
        return userService.getAllUsers();
    }
   /* @GetMapping("/dipendenti")
    @PreAuthorize("hasAuthority('MANAGER') ")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsersDipendenti();
    }*/
     @GetMapping("/{id}/machine")
    public List<UserMachineDto> getUserData(@PathVariable Integer id){
        return userService.getUserData(id);
    }
}
