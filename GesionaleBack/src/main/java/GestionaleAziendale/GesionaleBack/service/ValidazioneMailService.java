package GestionaleAziendale.GesionaleBack.service;

import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import GestionaleAziendale.GesionaleBack.entity.utenti.ValidazioneMail;
import GestionaleAziendale.GesionaleBack.repository.UserRepository;
import GestionaleAziendale.GesionaleBack.repository.ValidazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
      //  user.setEnabled(false);
        userRepository.save(user);
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
        String subject = "Verify your email";
        String confirmationUrl = "http://localhost:8080/registrationConfirm?token=" + token;
        String message = "Please click the link below to verify your email address:\n" + confirmationUrl;

        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(email);
        emailMessage.setSubject(subject);
        emailMessage.setText(message);
        mailSender.send(emailMessage);
    }
    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, 60); // token valido per 60 minuti
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
        //user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

}
