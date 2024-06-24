package GestionaleAziendale.GesionaleBack.controller.machineController;

import GestionaleAziendale.GesionaleBack.dto.dtoMachine.PartsDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.machine.Parts;
import GestionaleAziendale.GesionaleBack.service.serviceMachine.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/machine/parts")
public class PartsController {

    @Autowired
    private PartsService partsService;
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('MANAGER') ")
    public List<Machine> addParts(@RequestBody @Validated List<PartsDto> partsList, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new RuntimeException("Richiesta non valida: " + validation.getAllErrors().stream().map(e -> e.getDefaultMessage()).reduce("", (s1, s2) -> s1 + "\n" + s2));
        }
        return partsService.addPartsList(partsList);
    }
    @GetMapping("")
    @PreAuthorize("hasAuthority('MANAGER') ")
    public List<Parts> getAllParts() {
        return partsService.getAllParts();
    }

}
