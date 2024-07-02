package GestionaleAziendale.GesionaleBack.controller.machineController;

import GestionaleAziendale.GesionaleBack.dto.dtoMachine.GenericMachineDto;
import GestionaleAziendale.GesionaleBack.dto.queryDto.ListMaschinDto;
import GestionaleAziendale.GesionaleBack.dto.queryDto.MachineGenericStatusDto;
import GestionaleAziendale.GesionaleBack.dto.tiket.MachineStatusInfo;
import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.machine.genericMachine.MachineGeneric;
import GestionaleAziendale.GesionaleBack.service.serviceMachine.GenericMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private GenericMachineService genericMachineService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('MANAGER') ")
    public Machine addMachine(@RequestPart("genericMachineDto") @Validated GenericMachineDto genericMachineDto,
                              @RequestPart("photo") MultipartFile photo, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new RuntimeException("Richiesta non valida: " + validation.getAllErrors().stream().map(e -> e.getDefaultMessage()).reduce("", (s1, s2) -> s1 + "\n" + s2));
        }
        return genericMachineService.addMachine(genericMachineDto,photo);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('MANAGER') ")
    public List<Machine> getAllMachinesAndSubclasses() {
        return genericMachineService.getAllMachinesAndSubclasses();
    }

    @GetMapping("/details")
    @PreAuthorize("hasAuthority('MANAGER') ")
    public List<ListMaschinDto> getAllMachines() {
        return genericMachineService.getAllMachinesDetails();
    }
    @GetMapping("/status")
    @PreAuthorize("hasAnyAuthority('MANAGER','DIPENDENTE')")
    public List<MachineGenericStatusDto> getMachineStatus(Principal principal) {
        return genericMachineService.getMachineStatus(principal);
    }




}
