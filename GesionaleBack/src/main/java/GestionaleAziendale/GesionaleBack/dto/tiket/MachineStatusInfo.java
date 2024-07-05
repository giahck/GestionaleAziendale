package GestionaleAziendale.GesionaleBack.dto.tiket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@NoArgsConstructor
public class MachineStatusInfo {
    private String status;
    private String machineName;
    private String lastActiveTime;
}
