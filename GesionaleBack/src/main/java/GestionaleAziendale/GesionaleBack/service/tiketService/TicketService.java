package GestionaleAziendale.GesionaleBack.service.tiketService;

import GestionaleAziendale.GesionaleBack.ConfigurationApp.PdfCrea;
import GestionaleAziendale.GesionaleBack.dto.queryDto.MachineGenericStatusDto;
import GestionaleAziendale.GesionaleBack.dto.tiket.TicketAddDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.machine.Parts;
import GestionaleAziendale.GesionaleBack.entity.ticket.Ticket;
import GestionaleAziendale.GesionaleBack.entity.utenti.Users;
import GestionaleAziendale.GesionaleBack.enums.PriorityTicketEnum;
import GestionaleAziendale.GesionaleBack.enums.StatoMaschineEnum;
import GestionaleAziendale.GesionaleBack.maperDto.ticket.TicketMapper;
import GestionaleAziendale.GesionaleBack.repository.TicketRepository;
import GestionaleAziendale.GesionaleBack.repository.UserRepository;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.MachineGenericRepository;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.MachineRepository;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TicketService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MachineGenericRepository machineGenericRepository;
    @Autowired
    public ApplicationEventPublisher eventPublisher;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private PartsRepository partRepository;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private PdfCrea pdfCreator;
    public Ticket addTicket(TicketAddDto ticketAddDTO) throws IOException {
        Ticket ticket = ticketMapper.toEntity(ticketAddDTO);
        Users user = userRepository.findById(ticketAddDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        ticket.setUser(user);
        List<Parts> parts = ticketAddDTO.getPartIds().stream()
                .map(partId -> partRepository.findById(partId)
                        .orElseThrow(() -> new RuntimeException("Part not found")))
                .collect(Collectors.toList());
        ticket.setParts(parts);
        StatoMaschineEnum newStatus = prirityTiket(ticket.getPriority());

        for (Parts part : parts) {
            Machine machine = part.getMachine();
            if (machine != null) {
                machine.setStatoMaschine(newStatus);
                machineRepository.save(machine);
                List<MachineGenericStatusDto> machines = updateMachineStatusAndSendPdf(machineGenericRepository.findMachineStatus());
                messagingTemplate.convertAndSend("/topic/machineStatus", machines);
            }
        }




      //  System.out.println(parts);
  /*      if (ticket.getPriority() == PriorityTicketEnum.ALTA) {
            for (Parts part : parts) {
                Machine machine = part.getMachine(); // Recupera la macchina associata alla parte
                if (machine != null) {
                    machine.setStatoMaschine(StatoMaschineEnum.GUASTA); // Imposta lo stato della macchina a GUASTA

                    machineRepository.save(machine);
                    //messagingTemplate.convertAndSend("/topic/machineStatus", machineGenericRepository.findMachineStatus());

                    List<MachineGenericStatusDto> machines=  updateMachineStatusAndSendPdf( machineGenericRepository.findMachineStatus());
                    messagingTemplate.convertAndSend("/topic/machineStatus", machines);
                }
            }
        }*/
        return ticketRepository.save(ticket);
    }
    private StatoMaschineEnum prirityTiket(PriorityTicketEnum priority) {
        switch (priority) {
            case EMERGENZA:
                return StatoMaschineEnum.MANUTENZIONE;
            case URGENZA:
                return StatoMaschineEnum.INATTIVA;
            case ALTA:
                return StatoMaschineEnum.GUASTA;
            case MEDIA:
            case BASSA:
                return StatoMaschineEnum.ATTIVA;
            default:
                throw new IllegalArgumentException("Unknown priority: " + priority);
        }
    }

    public List<MachineGenericStatusDto> updateMachineStatusAndSendPdf(List<MachineGenericStatusDto> machines) throws IOException {
        for (MachineGenericStatusDto machine : machines) {
            byte[] pdfBytes = pdfCreator.createPdf(machine); // Genera il PDF per la macchina specificata
            String encodedPdf = Base64.getEncoder().encodeToString(pdfBytes);
            machine.setPdfContent(encodedPdf);
        }


        return machines;
    }



    /*public void updateMachineStatus(Long machineId, StatoMaschineEnum newStatus) {
        // Logica per aggiornare lo stato della macchina nel database

        // Dopo l'aggiornamento, ottieni il nuovo stato della macchina
        MachineGenericStatusDto updatedMachineStatus = fetchUpdatedMachineStatus(machineId);

        // Invia l'aggiornamento tramite WebSocket
        messagingTemplate.convertAndSend("/topic/machineStatusUpdates", updatedMachineStatus);
    }*/

   /* private MachineGenericStatusDto fetchUpdatedMachineStatus(Long machineId) {
        // Implementa la logica per recuperare lo stato aggiornato della macchina
        // Esempio fittizio:
        MachineGenericStatusDto machineStatusDto = new MachineGenericStatusDto();
        machineStatusDto.setDescription("Descrizione aggiornata");
        machineStatusDto.setMatricola("M0001");
        machineStatusDto.setPhoto("photo-url");
        machineStatusDto.setNomeMacchina("Macchina A");
        machineStatusDto.setMarca("Marca A");
        machineStatusDto.setModello("Modello A");
        machineStatusDto.setStatoMaschine(StatoMaschineEnum.IN_MANUTENZIONE);
        return machineStatusDto;
    }*/
}
