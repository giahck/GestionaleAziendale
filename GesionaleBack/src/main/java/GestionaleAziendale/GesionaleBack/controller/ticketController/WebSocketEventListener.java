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

@Component
public class WebSocketEventListener {

    @Autowired
    private SimpMessagingTemplate template;



    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        // Check if the subscription is for the /topic/machineStatus topic
        String destination = event.getMessage().getHeaders().get("simpDestination").toString();
        if ("/topic/machineStatus".equals(destination)) {
            MachineStatusInfo info = new MachineStatusInfo();
            info.setStatus("Attivo");
            info.setMachineName("Stampante 3D");
            info.setLastActiveTime("2023-04-01T12:34:56");

            // Send the status info to the client
            template.convertAndSend(destination, info);
        }
    }
}
