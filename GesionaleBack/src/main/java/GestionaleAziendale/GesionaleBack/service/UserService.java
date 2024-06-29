package GestionaleAziendale.GesionaleBack.service;




import GestionaleAziendale.GesionaleBack.dto.UserDto;
import GestionaleAziendale.GesionaleBack.dto.UserMachineDto;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    @Autowired
    private JdbcTemplate jdbcTemplate;

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

//metodo template jdbcTemplate

    public List<UserMachineDto> getUserData(int userId) {
        String sql = "SELECT u.id as user_id,  " +
                "m.id as machine_id, m.nome_macchina, m.marca, m.stato_macchina, mg.description, mg.photo," +
                "p.id as part_id, p.nome_parte, p.descrizione, p.note, p.quantity_parts, " +
                "pc.id as piece_id, pc.nome_pezzo, pc.quantity_piece, pc.descrizione " +
                "FROM users u " +
                "JOIN utente_competenza uc ON u.id = uc.utente_id " +
                "LEFT JOIN competenze c ON uc.competenza_id = c.id_competenza " +
                "LEFT JOIN machines m ON c.id_machine = m.id " +
                "LEFT JOIN machines_generic mg ON m.id = mg.id " +
                "LEFT JOIN parts p ON m.id = p.id_machine " +
                "LEFT JOIN pieces pc ON p.id = pc.id_parts " +
                "WHERE u.id = ?";

        return jdbcTemplate.query(con -> {
            PreparedStatement ps = con.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, userId);
            return ps;
        }, this::mapRow);
    }

    private List<UserMachineDto> mapRow(ResultSet rs) throws SQLException {
        Map<Integer, UserMachineDto> userMap = new HashMap<>();
        Map<Integer, UserMachineDto.MachineDto> machineMap = new HashMap<>();
        Map<Integer, UserMachineDto.MachineDto.PartDto> partMap = new HashMap<>();

        while (rs.next()) {
            int userId = rs.getInt("user_id");
            UserMachineDto userDto = userMap.get(userId);
            if (userDto == null) {
                userDto = new UserMachineDto();
                userDto.setUserId(userId);
                userDto.setMachines(new ArrayList<>());
                userMap.put(userId, userDto);
            }

            int machineId = rs.getInt("machine_id");
            UserMachineDto.MachineDto machineDto = machineMap.get(machineId);
            if (machineDto == null) {
                machineDto = new UserMachineDto.MachineDto();
                machineDto.setMachineId(machineId);
                machineDto.setNomeMacchina(rs.getString("nome_macchina"));
                machineDto.setMarca(rs.getString("marca"));
                machineDto.setStatoMacchina(rs.getString("stato_macchina"));
                machineDto.setDescription(rs.getString("description"));
                machineDto.setPhoto(rs.getString("photo"));
                machineDto.setParts(new ArrayList<>());
                machineMap.put(machineId, machineDto);
                userDto.getMachines().add(machineDto);
            }

            int partId = rs.getInt("part_id");
            UserMachineDto.MachineDto.PartDto partDto = partMap.get(partId);
            if (partDto == null) {
                partDto = new UserMachineDto.MachineDto.PartDto();
                partDto.setPartId(partId);
                partDto.setNomeParte(rs.getString("nome_parte"));
                partDto.setDescrizione(rs.getString("descrizione"));
                partDto.setNote(rs.getString("note"));
                partDto.setQuantityParts(rs.getInt("quantity_parts"));
                partDto.setPieces(new ArrayList<>());
                partMap.put(partId, partDto);
                machineDto.getParts().add(partDto);
            }

            // Aggiungi il pezzo alla parte corrente
            UserMachineDto.MachineDto.PartDto.PieceDto pieceDto = new UserMachineDto.MachineDto.PartDto.PieceDto();
            pieceDto.setPieceId(rs.getInt("piece_id"));
            pieceDto.setNomePezzo(rs.getString("nome_pezzo"));
            pieceDto.setQuantityPiece(rs.getInt("quantity_piece"));
            pieceDto.setDescrizione(rs.getString("descrizione"));
            partDto.getPieces().add(pieceDto);
        }

        return new ArrayList<>(userMap.values());
    }

  /*  @Transactional(readOnly = true)
    public List<MachineGeneric> getUserDatad(int userId) {
        Session currentSession = entityManager.unwrap(Session.class);

        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<MachineGeneric> criteria = builder.createQuery(MachineGeneric.class);
        Root<MachineGeneric> root = criteria.from(MachineGeneric.class);

        criteria.select(root);

        // Joining necessari
        root.join("competenza", JoinType.LEFT);
        root.join("parts", JoinType.LEFT);

        // Applicazione dei filtri
        criteria.where(builder.equal(root.get("users").get("id"), userId));

        List<MachineGeneric> machines = currentSession.createQuery(criteria).getResultList();

        return machines;
    }*/
}

