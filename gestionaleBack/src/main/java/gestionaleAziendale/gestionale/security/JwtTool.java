package project.epic_energy_back.security;

import project.epic_energy_back.entities.Utente;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import project.epic_energy_back.exceptions.UnauthorizedException;

import java.util.Date;

@Component
public class JwtTool {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.duration}")
    private long duration;


    //crea token impostando data inizio, data fine, id utente e firma del token attraverso la chiave segreta
    public String createToken(Utente utente) {
        return Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis())).
                expiration(new Date(System.currentTimeMillis() + duration)).
                subject(String.valueOf(utente.getId())).
                signWith(Keys.hmacShaKeyFor(secret.getBytes())).
                compact();
    }

    //effettua verifica token ricevuto. Verifica veridicita e scadenza
    public void verifyToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).
                    build().parse(token);
        } catch (Exception e) {
            throw new UnauthorizedException("Error in authorization, relogin!");
        }
    }

    public int getIdFromToken(String token){
        return Integer.parseInt(Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).
                build().parseSignedClaims(token).getPayload().getSubject());
    }
}