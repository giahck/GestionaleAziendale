package GestionaleAziendale.GesionaleBack.service.serviceMachine;

import GestionaleAziendale.GesionaleBack.dto.dtoMachine.GenericMachineDto;
import GestionaleAziendale.GesionaleBack.dto.queryDto.ListMaschinDto;
import GestionaleAziendale.GesionaleBack.dto.queryDto.MachineGenericStatusDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.machine.Parts;
import GestionaleAziendale.GesionaleBack.entity.machine.genericMachine.MachineGeneric;
import GestionaleAziendale.GesionaleBack.entity.utenti.Competenza;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import GestionaleAziendale.GesionaleBack.maperDto.mapperDtoMachine.MachineGenericMapper;
import GestionaleAziendale.GesionaleBack.repository.UserRepository;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.MachineGenericRepository;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.MachineRepository;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.PartsRepository;
import GestionaleAziendale.GesionaleBack.service.CompetenzeService;
import com.cloudinary.Cloudinary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.BooleanUtils.forEach;

@Service
public class GenericMachineService {
    private static final Logger log = LoggerFactory.getLogger(GenericMachineService.class);
    @Autowired
    private MachineGenericMapper machineGenericMapper;
    @Autowired
    private MachineGenericRepository machineGenericRepository;
    @Autowired
    private PartsRepository partsRepository;
    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CompetenzeService competenzeService;
    @Autowired
    private UserRepository userRepository;

    @Transactional

    public Machine addMachine(GenericMachineDto genericMachineDto, MultipartFile fotoMachine) {
        if (fotoMachine != null && !fotoMachine.isEmpty()) {
            System.out.println("Photo not null");
            try {
                String url = (String) cloudinary.uploader().upload(fotoMachine.getBytes(), null).get("url");
                genericMachineDto.setPhoto(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        MachineGeneric machineGeneric = machineGenericMapper.genericDtoToMachine(genericMachineDto);
        if (genericMachineDto.getPartsId() != null && !genericMachineDto.getPartsId().isEmpty()) {
            machineGeneric.setParts(genericMachineDto.getPartsId().stream().map(id -> {
                Parts parts = partsRepository.findById(id).orElse(null);
                if (parts == null) {
                    System.out.println("Part not found");
                }
                return parts;
            }).collect(Collectors.toList()));
        }
        Competenza competenze = competenzeService.getCompetenzeById(genericMachineDto.getCompetenzaId());
        if (competenze != null) {
            machineGeneric.setCompetenza(competenze);
            competenze.setMachine(machineGeneric);
        }

        return machineGenericRepository.save(machineGeneric);
    }

    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    public List<Machine> getAllMachinesAndSubclasses() {
        return machineRepository.findAllMachinesAndSubclasses();
    }

    public Machine getMachineById(int id) {
        Optional<Machine> optionalMachine = machineRepository.findById(id);
        if (!optionalMachine.isPresent()) {
            throw new IllegalArgumentException("Machine with id " + id + " not found");
        }
        return optionalMachine.get();
    }

    public List<ListMaschinDto> getAllMachinesDetails() {
        List<ListMaschinDto> machineDetails = machineRepository.findSelectedMachineDetailsForCompetenze();

        for (ListMaschinDto machineDetail : machineDetails) {
            Competenza competenza = machineDetail.getCompetenza();
            if (competenza != null) {
                List<ListMaschinDto.UserNameDto> users = userRepository.findUserNamesByCompetenzaId(competenza.getIdCompetenza());
                machineDetail.setUtenti(users);
            }
        }

        return machineDetails;
    }

    public List<MachineGenericStatusDto> getMachineStatus(Principal principal) {
        System.out.println("Principal: " + principal.getName());

        Optional<Users> userOptional = userRepository.findByEmail(principal.getName());
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("MANAGER"))) {
                return machineGenericRepository.findMachineStatus();
            } else {
                return machineGenericRepository.findMachineStatusByUserCompetenze(new ArrayList<>(user.getCompetenze()));
            }
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }


    }
