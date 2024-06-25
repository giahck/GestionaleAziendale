package GestionaleAziendale.GesionaleBack.service;


import GestionaleAziendale.GesionaleBack.dto.CompetenzeRegDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.utenti.Competenza;


import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import GestionaleAziendale.GesionaleBack.exeptions.BadRequestException;
import GestionaleAziendale.GesionaleBack.maperDto.CompetenzaMapper;
import GestionaleAziendale.GesionaleBack.repository.CompetenzaRepository;
import GestionaleAziendale.GesionaleBack.repository.RuoloRepository;
import GestionaleAziendale.GesionaleBack.repository.UserRepository;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompetenzeService {
    @Autowired
    private CompetenzaRepository competenzaRepository;
    @Autowired
    private CompetenzaMapper competenzaMapper;
    @Autowired
    private RuoloRepository ruoloRepository;
    @Autowired
    private UserRepository utentiRepository;
@Autowired
private MachineRepository machineRepository;

    public CompetenzeRegDto saveCompetenza(CompetenzeRegDto competenzeDto) {
        Competenza competenza = competenzaMapper.toEntity(competenzeDto);
        Set<Integer> userIds = competenzeDto.getUsersId();
        for (Integer userId : userIds) {
            Users user = utentiRepository.findById(userId).orElse(null);
            System.out.println("User: " + user);
            if (user != null) {
                user.getCompetenze().add(competenza);
                competenza.addUser(user);
            } else {
                throw new BadRequestException("L'utente con id " + userId + " non esiste");
            }
        }
        Machine machine = machineRepository.findById(competenzeDto.getMachineId()).orElse(null);
        if (machine != null) {
            competenza.setMachine(machine);
        } else {
            throw new BadRequestException("La macchina con id " + competenzeDto.getMachineId() + " non esiste");
        }
        competenza = competenzaRepository.save(competenza);



        System.out.println(competenza);
        return null;
    }

   /* public CompetenzeRegDto saveCompetenza(CompetenzeRegDto competenzeDto) {
        Set<Integer> userIds = competenzeDto.getUsersId();
        System.out.println(competenzeDto);
        Set<Users> usersSet = new HashSet<>();
        List<Integer> loadedUserIds = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();

        // Primo salvataggio: Salva la competenza senza gli utenti
        Competenza competenza = competenzaMapper.toEntity(competenzeDto);
       // competenza = competenzaRepository.save(competenza);

        // Aggiungi gli utenti alla competenza
        for (Integer userId : userIds) {
            Users user = utentiRepository.findById(userId).orElse(null);
            if (user != null) {
                usersSet.add(user);
                user.getCompetenze().add(competenza); // Aggiungi la competenza all'utente
                loadedUserIds.add(userId);
            } else {
                errorMessages.add("L'utente con id " + userId + " non esiste");
            }
        }

        // Aggiorna la competenza con gli utenti
        competenza.setUsersId(usersSet);
        competenza = competenzaRepository.save(competenza);

        CompetenzeRegDto competenzeRegDto = competenzaMapper.toDto(competenza);

        // Throw an exception if there were any errors
        if (!errorMessages.isEmpty()) {
            throw new BadRequestException(String.join(". ", errorMessages) + ". Gli utenti caricati sono: " + loadedUserIds);
        }

        return competenzeRegDto;
    }*/
    public Competenza getCompetenzeById(int id) {
        Competenza competenza = competenzaRepository.findById(id).orElse(null);
        System.out.println("Retrieved Competenza: " + competenza);
        return competenza;
    }
}

