package gestionaleAziendale.gestionale.dto;

import gestionaleAziendale.gestionale.enums.RuoloEnum;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto{
    private String email;
    private String cognome;
    private String nome;
    private String sesso;
    private Date dataDiNascita;
    private String comuneDiNascita;
    private String codiceFiscale;
    private Long telefono;
    private String indirizzo;
    private String cap;
    private String provincia;
    private String password;
    private RuoloEnum ruolo;
}
