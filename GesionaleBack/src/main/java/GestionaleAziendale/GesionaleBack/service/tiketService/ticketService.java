package GestionaleAziendale.GesionaleBack.service.tiketService;

import GestionaleAziendale.GesionaleBack.dto.queryDto.MachineGenericStatusDto;
import GestionaleAziendale.GesionaleBack.enums.StatoMaschineEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ticketService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ApplicationEventPublisher eventPublisher;

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
