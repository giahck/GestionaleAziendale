package GestionaleAziendale.GesionaleBack.controller.machineController;

import GestionaleAziendale.GesionaleBack.dto.dtoMachine.GenericMachineDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.machine.genericMachine.MachineGeneric;
import GestionaleAziendale.GesionaleBack.service.serviceMachine.GenericMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/machine")

public class MachineController {

    @Autowired
    private GenericMachineService genericMachineService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('MANAGER') ")
    public Machine addMachine(@RequestPart @Validated GenericMachineDto genericMachineDto, @RequestPart("fotoMachine") MultipartFile fotoMachine, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new RuntimeException("Richiesta non valida: " + validation.getAllErrors().stream().map(e -> e.getDefaultMessage()).reduce("", (s1, s2) -> s1 + "\n" + s2));
        }
        return genericMachineService.addMachine(genericMachineDto,fotoMachine);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('MANAGER') ")
    public List<Machine> getAllMachinesAndSubclasses() {
        return genericMachineService.getAllMachinesAndSubclasses();
    }

}
