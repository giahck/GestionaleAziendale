package GestionaleAziendale.GesionaleBack.entity.utenti;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "users")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String email;
    private String cognome;
    private String nome;
    private String sesso;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataDiNascita;
    private String comuneDiNascita;
    @Pattern(regexp = "[A-Z]{6}\\d\\d[A-Z]\\d\\d[A-Z]\\d\\d\\d[A-Z]", message = "Invalid tax code format")
    @Column(nullable = false, unique = true)
    private String codiceFiscale;
    private Long telefono;
    private String indirizzo;
    private String cap;
    private String provincia;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utente_ruoli",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
    private Set<Ruolo> ruolo = new HashSet<>();


    @Column(nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ruolo.stream()
                .map(ruolo -> new SimpleGrantedAuthority(ruolo.getNomeRuolo().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public void setRuoli(Set<Ruolo> ruoli) {
        this.ruolo = ruoli;
    }
}