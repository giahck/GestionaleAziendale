package GestionaleAziendale.GesionaleBack.service;



import GestionaleAziendale.GesionaleBack.dto.UserDto;
import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import GestionaleAziendale.GesionaleBack.exeptions.ResourceNotFoundException;
import GestionaleAziendale.GesionaleBack.repository.RuoloRepository;
import GestionaleAziendale.GesionaleBack.repository.UserRepository;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
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


        // Fetch Ruolo entities by their IDs and convert List to Set
        Set<Ruolo> ruoli = new HashSet<>(ruoloRepository.findAllById(userDto.getRuoloId()));
        // Set the fetched Ruolo entities to the user
        user.setRuoli(ruoli);

        // Save user to database
        user = userRepository.save(user);

        // Convert Users entity back to UserDto
        UserDto savedUserDto = new UserDto();
        savedUserDto.setEmail(user.getEmail());
        savedUserDto.setCognome(user.getCognome());
        savedUserDto.setNome(user.getNome());
        savedUserDto.setSesso(user.getSesso());
        savedUserDto.setDataDiNascita(convertToLocalDate(user.getDataDiNascita()));
        savedUserDto.setComuneDiNascita(user.getComuneDiNascita());
        savedUserDto.setCodiceFiscale(user.getCodiceFiscale());
        savedUserDto.setTelefono(user.getTelefono());
        savedUserDto.setIndirizzo(user.getIndirizzo());
        savedUserDto.setCap(user.getCap());
        savedUserDto.setProvincia(user.getProvincia());
        savedUserDto.setPassword(null); // Do not return the password

        // Convert Set<Ruolo> to Set<Integer>
        savedUserDto.setRuoloId(user.getRuolo().stream().map(Ruolo::getIdRuolo).collect(Collectors.toSet()));

        return savedUserDto;
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
}