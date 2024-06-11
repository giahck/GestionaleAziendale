package GestionaleAziendale.GesionaleBack.service;



import GestionaleAziendale.GesionaleBack.dto.LoginDto;
import GestionaleAziendale.GesionaleBack.dto.UserDto;
import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import GestionaleAziendale.GesionaleBack.exeptions.ResourceNotFoundException;
import GestionaleAziendale.GesionaleBack.repository.RuoloRepository;
import GestionaleAziendale.GesionaleBack.repository.UserRepository;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import GestionaleAziendale.GesionaleBack.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RuoloRepository ruoloRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTool jwtTool;
    public UserDto saveUser(UserDto userDto) {
        // Convert UserDto to Users entity
        Users user = new Users();
        user.setEmail(userDto.getEmail());
        user.setCognome(userDto.getCognome());
        user.setNome(userDto.getNome());
        user.setSesso(userDto.getSesso());
        user.setDataDiNascita(java.sql.Date.valueOf(userDto.getDataDiNascita()));
        user.setComuneDiNascita(userDto.getComuneDiNascita());
        user.setCodiceFiscale(userDto.getCodiceFiscale());
        user.setTelefono(userDto.getTelefono());
        user.setIndirizzo(userDto.getIndirizzo());
        user.setCap(userDto.getCap());
        user.setProvincia(userDto.getProvincia());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encrypt password

        Set<Ruolo> ruoli = new HashSet<>(ruoloRepository.findAllById(userDto.getRuoloId()));
        if (ruoli.size() != userDto.getRuoloId().size()) {
            throw new ResourceNotFoundException("Ruolo non trovato nel sistema");
        }
        user.setRuoli(ruoli);

        user = userRepository.save(user);

        UserDto userDtoConvert = mapUserToUserDto(user);
        return userDtoConvert;
    }
    private LocalDate convertToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


    public Users getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con id: "+id));
    }
    public Optional<Users> checkUser(String email) {
        return userRepository.findByEmail(email);
    }

    public UserDto authenticateUserAndCreateToken(LoginDto loginDto) {
        Optional<Users> userOptional = userRepository.findByEmail(loginDto.getEmail());

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                UserDto userDto = mapUserToUserDto(user);
                userDto.setAccessToken(jwtTool.createToken(user));
                return userDto;
            } else {
                throw new ResourceNotFoundException("Errore nel login, riloggarsi");
            }

        } else {
            throw new ResourceNotFoundException("Utente con email " + loginDto.getEmail() + "non trovato ");
        }
    }
    private UserDto mapUserToUserDto(Users user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setCognome(user.getCognome());
        userDto.setNome(user.getNome());
        userDto.setSesso(user.getSesso());
        userDto.setDataDiNascita(convertToLocalDate(user.getDataDiNascita()));
        userDto.setComuneDiNascita(user.getComuneDiNascita());
        userDto.setCodiceFiscale(user.getCodiceFiscale());
        userDto.setTelefono(user.getTelefono());
        userDto.setIndirizzo(user.getIndirizzo());
        userDto.setCap(user.getCap());
        userDto.setProvincia(user.getProvincia());
        userDto.setRuoloId(user.getRuolo().stream().map(Ruolo::getIdRuolo).collect(Collectors.toSet()));
        return userDto;
    }
}