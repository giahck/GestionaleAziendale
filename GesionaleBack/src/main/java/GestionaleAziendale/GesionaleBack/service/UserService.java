package GestionaleAziendale.GesionaleBack.service;


import GestionaleAziendale.GesionaleBack.dto.UserDto;
import GestionaleAziendale.GesionaleBack.dto.UserRegDto;
import GestionaleAziendale.GesionaleBack.dto.UsersDatiDto;
import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import GestionaleAziendale.GesionaleBack.enums.RuoloEnum;
import GestionaleAziendale.GesionaleBack.exeptions.ResourceNotFoundException;
import GestionaleAziendale.GesionaleBack.maperDto.UserMapper;
import GestionaleAziendale.GesionaleBack.maperDto.UserRegisterMapper;
import GestionaleAziendale.GesionaleBack.maperDto.users.UsersDatiMapper;
import GestionaleAziendale.GesionaleBack.repository.RuoloRepository;
import GestionaleAziendale.GesionaleBack.repository.UserRepository;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    UserRegisterMapper userRegisterMapper;
    @Autowired
    ValidazioneMailService validazioneMailService;
    @Autowired
    private UsersDatiMapper usersDatiMapper;

    public Users saveUserRegister(UserRegDto userDto) {

        Users user = userRegisterMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<Ruolo> ruoli = new HashSet<>();
        ruoli.add(checkRuolo(RuoloEnum.USER));
        user.setRuoli(ruoli);
        userRepository.save(user);
        validazioneMailService.registerUser(user);
        //  userRegisterMapper.toDto(user);
        return checkEmail(user.getEmail());
    }


    public UserDto saveUser(UserDto userDto) {
        Users user = userMapper.userDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encrypt password

        Set<Ruolo> ruoli = new HashSet<>(ruoloRepository.findAllById(userDto.getRuoloId()));
        if (!userDto.getRuoloId().isEmpty() && ruoli.size() != userDto.getRuoloId().size()) {
            throw new ResourceNotFoundException("Ruolo non trovato nel sistema");
        }
        user.setRuoli(ruoli);

        user = userRepository.save(user);

        return userMapper.userToUserDto(user);
    }

    public boolean isEmailConfirmed(int id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con id: " + id)).getEnabled();


    }

    public Users getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con id: " + id));
    }

    public Users checkEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con email: " + email));
    }

    public Ruolo checkRuolo(RuoloEnum ruoloEnum) {
        return ruoloRepository.findByNomeRuolo(ruoloEnum)
                .orElseThrow(() -> new ResourceNotFoundException("Ruolo non trovato: " + ruoloEnum));
    }

    public List<UsersDatiDto> getAllUsers() {
        List<Users> users = userRepository.findAll();

        return usersDatiMapper.usersToUsersDatiDto(users);
    }

}