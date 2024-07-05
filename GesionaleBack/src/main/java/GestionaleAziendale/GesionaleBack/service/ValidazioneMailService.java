package GestionaleAziendale.GesionaleBack.service;

import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import GestionaleAziendale.GesionaleBack.entity.utenti.ValidazioneMail;
import GestionaleAziendale.GesionaleBack.repository.UserRepository;
import GestionaleAziendale.GesionaleBack.repository.ValidazioneRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class ValidazioneMailService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ValidazioneRepository tokenRepository;
    @Autowired
    private JavaMailSender mailSender;

    public void registerUser(Users user) {
        String token = UUID.randomUUID().toString();
        createVerificationToken(user, token);
        sendVerificationEmail(user.getEmail(), token);
    }

    public void createVerificationToken(Users user, String token) {
        ValidazioneMail verificationToken = new ValidazioneMail();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(calculateExpiryDate());
        tokenRepository.save(verificationToken);
    }

    public void sendVerificationEmail(String email, String token) {
        String subject = "Verifica il tuo indirizzo email";
        String confirmationUrl = "http://localhost:8080/auth/registrationConfirm?token=" + token;
        String message = "<html>"
                + "<body>"
                + "<h1>Conferma la tua email</h1>"
                + "<p>Grazie per esserti registrato. Per completare la registrazione, clicca sul link qui sotto:</p>"
                + "<a href='" + confirmationUrl + "'>Conferma la tua email</a>"
                + "<p>Se non hai richiesto questa registrazione, ignora questa email.</p>"
                + "</body>"
                + "</html>";

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(message, true); // true indica che il messaggio è in HTML
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Problema nell'invio dell'email di verifica", e);
        }
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, 10000); // token valido per 60 minuti
        return new Date(cal.getTime().getTime());
    }

    public String validateVerificationToken(String token) {
        ValidazioneMail verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "invalidToken";
        }
        Users user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            tokenRepository.delete(verificationToken);
            return "expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "<html><head><meta http-equiv='refresh' content='5;url=http://localhost:4200/' /></head><body>Email confermata. Verrai reindirizzato tra 5 secondi.</body></html>";
    }
}
