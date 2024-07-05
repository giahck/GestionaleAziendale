package GestionaleAziendale.GesionaleBack.controller.ticketController;

import GestionaleAziendale.GesionaleBack.dto.tiket.MachineStatusInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

//@Component
public class WebSocketEventListenerMacchina {

   /* @Autowired
    private SimpMessagingTemplate template;*/

  /*  @EventListener
    public void handleMachineStatusChange(MachineStatusChangeEvent event) {
        Machine machine = event.getMachine();
        // Invia una notifica ai client tramite WebSocket
        template.convertAndSend("/topic/machineStatusChange", machine);
    }*/



}
