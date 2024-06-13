package GestionaleAziendale.GesionaleBack.controller;

import GestionaleAziendale.GesionaleBack.dto.LoginDto;
import GestionaleAziendale.GesionaleBack.dto.UserDto;
import GestionaleAziendale.GesionaleBack.dto.UserRegDto;
import GestionaleAziendale.GesionaleBack.exeptions.BadRequestException;
import GestionaleAziendale.GesionaleBack.service.AuthService;
import GestionaleAziendale.GesionaleBack.service.UserService;
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
    @PostMapping("/register")
    public UserRegDto Register(@RequestBody @Validated UserRegDto userDto, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
          return userService.saveUserRegister(userDto);
    }
    @PostMapping("/login")
    public UserDto Login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        try {
            // Pause for 3 seconds
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Handle exception
            e.printStackTrace();
        }
        return authService.authenticateUserAndCreateToken(loginDto);
    }
    /*@GetMapping("/registrationConfirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if ("valid".equals(result)) {
            return ResponseEntity.ok("Account verified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
        }
    }*/
}