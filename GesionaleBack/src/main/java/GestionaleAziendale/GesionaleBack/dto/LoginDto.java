package GestionaleAziendale.GesionaleBack.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
