package GestionaleAziendale.GesionaleBack.controller;

import GestionaleAziendale.GesionaleBack.dto.LoginDto;
import GestionaleAziendale.GesionaleBack.dto.UserDto;
import GestionaleAziendale.GesionaleBack.dto.UserRegDto;
import GestionaleAziendale.GesionaleBack.exeptions.BadRequestException;
import GestionaleAziendale.GesionaleBack.service.AuthService;
import GestionaleAziendale.GesionaleBack.service.UserService;
import GestionaleAziendale.GesionaleBack.service.ValidazioneMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private ValidazioneMailService validazioneMailService;
    @PostMapping("/register")
    public Integer Register(@RequestBody @Validated UserRegDto userDto, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
          return userService.saveUserRegister(userDto).getId();
    }
    @PostMapping("/login")
    public UserDto Login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return authService.authenticateUserAndCreateToken(loginDto);
    }
    @GetMapping("/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") String token) {
        String result = validazioneMailService.validateVerificationToken(token);
        if ("valid".equals(result)) {
            return result;
        } else {
            return result;
        }
    }
    @GetMapping("/email-confirmed")
    public boolean isEmailConfirmed(@RequestParam("id") Integer id) {

        return userService.isEmailConfirmed(id);
    }
}