package GestionaleAziendale.GesionaleBack.service;

import GestionaleAziendale.GesionaleBack.dto.LoginDto;
import GestionaleAziendale.GesionaleBack.dto.UserDto;
import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import GestionaleAziendale.GesionaleBack.exeptions.ResourceNotFoundException;
import GestionaleAziendale.GesionaleBack.maperDto.UserMapper;
import GestionaleAziendale.GesionaleBack.repository.UserRepository;
import GestionaleAziendale.GesionaleBack.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTool jwtTool;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired

    private UserRepository userRepository;
    @Autowired

    private UserMapper userMapper;
    public UserDto authenticateUserAndCreateToken(LoginDto loginDto) {
        Optional<Users> userOptional = userRepository.findByEmail(loginDto.getEmail());
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                System.out.println(passwordEncoder.matches(loginDto.getPassword(), user.getPassword()));
               UserDto userDto = userMapper.userToUserDto(user);
               userDto.setAccessToken(jwtTool.createToken(user));
                if (loginDto.getRememberMe() != null) {
                    user.setRememberMe(loginDto.getRememberMe());
                    userRepository.save(user);
                }
                userDto.setId(user.getId());
                return userDto;
            } else {
                throw new ResourceNotFoundException("Errore nel login, riloggarsi");
            }

        } else {
            throw new ResourceNotFoundException("Utente con email " + loginDto.getEmail() + "non trovato ");
        }
    }
    /*private UserDto mapUserToUserDto(Users user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setCognome(user.getCognome());
        userDto.setNome(user.getNome());
        userDto.setSesso(user.getSesso());
        userDto.setDataDiNascita((user.getDataDiNascita()));
        userDto.setComuneDiNascita(user.getComuneDiNascita());
        userDto.setCodiceFiscale(user.getCodiceFiscale());
        userDto.setTelefono(user.getTelefono());
        userDto.setIndirizzo(user.getIndirizzo());
        userDto.setCap(user.getCap());
        userDto.setProvincia(user.getProvincia());
        userDto.setRuoloId(user.getRuolo().stream().map(Ruolo::getIdRuolo).collect(Collectors.toSet()));
        return userDto;
    }*/
}
