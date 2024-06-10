package GestionaleAziendale.GesionaleBack.controller;

import GestionaleAziendale.GesionaleBack.dto.UserDto;
import GestionaleAziendale.GesionaleBack.exeptions.BadRequestException;
import GestionaleAziendale.GesionaleBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDto Register(@RequestBody @Validated UserDto userDto, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
          return userService.saveUser(userDto);


    }
}