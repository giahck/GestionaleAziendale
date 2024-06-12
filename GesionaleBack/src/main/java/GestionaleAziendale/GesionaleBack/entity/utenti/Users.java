package GestionaleAziendale.GesionaleBack.entity.utenti;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;
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
    private LocalDate dataDiNascita;
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

    @ManyToMany
    @JoinTable(
            name = "utente_competenza",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "competenza_id"))
    private Set<Competenza> competenze= new HashSet<>();

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


    public void addCompetenza(Competenza competenza) {
        this.competenze.add(competenza);
        competenza.getUsersId().add(this);
    }
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                // do not include competenze
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return id == users.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
