package GestionaleAziendale.GesionaleBack.entity.utenti;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Entity
public class ValidazioneMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;

    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Users user;

    private Date expiryDate;
}
