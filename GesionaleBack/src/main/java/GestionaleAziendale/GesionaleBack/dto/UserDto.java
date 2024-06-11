package GestionaleAziendale.GesionaleBack.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDto {
    @NotBlank
    @Email(message = "Deve essere un indirizzo email valido")
    private String email;

    @NotBlank(message = "Il cognome non può essere vuoto")
    private String cognome;

    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il sesso non può essere vuoto")
    private String sesso;

    @NotNull
    @Past(message = "La data di nascita deve essere nel passato")
    private LocalDate dataDiNascita;

    @NotBlank(message = "Il comune di nascita non può essere vuoto")
    private String comuneDiNascita;

    @Pattern(regexp = "[A-Z]{6}\\d\\d[A-Z]\\d\\d[A-Z]\\d\\d\\d[A-Z]", message = "Formato del codice fiscale non valido")
    @NotBlank(message = "Il codice fiscale non può essere vuoto")
    private String codiceFiscale;

    @NotNull
    @PositiveOrZero(message = "Il numero di telefono deve essere un valore positivo o zero")
    private Long telefono;

    @NotBlank(message = "L'indirizzo non può essere vuoto")
    private String indirizzo;

    @NotBlank(message = "Il CAP non può essere vuoto")
    private String cap;

    @NotBlank(message = "La provincia non può essere vuota")
    private String provincia;

    @NotBlank(message = "La password non può essere vuota")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull(message = "Ruolo non può essere null")
    private Set<Integer> ruoloId;

    private String accessToken;
}