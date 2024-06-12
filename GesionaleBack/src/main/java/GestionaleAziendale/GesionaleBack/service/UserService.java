package GestionaleAziendale.GesionaleBack.service;



import GestionaleAziendale.GesionaleBack.dto.LoginDto;
import GestionaleAziendale.GesionaleBack.dto.UserDto;
import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import GestionaleAziendale.GesionaleBack.exeptions.ResourceNotFoundException;
import GestionaleAziendale.GesionaleBack.maperDto.UserMapper;
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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RuoloRepository ruoloRepository;
    @Autowired
    private UserMapper userMapper;

    public UserDto saveUser(UserDto userDto) {
        Users user = userMapper.userDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encrypt password

        Set<Ruolo> ruoli = new HashSet<>(ruoloRepository.findAllById(userDto.getRuoloId()));
        if (!userDto.getRuoloId().isEmpty() &&ruoli.size() != userDto.getRuoloId().size()) {
            throw new ResourceNotFoundException("Ruolo non trovato nel sistema");
        }
        user.setRuoli(ruoli);

        user = userRepository.save(user);

        return userMapper.userToUserDto(user);
    }


    public Users getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con id: "+id));
    }
    public Optional<Users> checkUser(String email) {
        return userRepository.findByEmail(email);
    }

}