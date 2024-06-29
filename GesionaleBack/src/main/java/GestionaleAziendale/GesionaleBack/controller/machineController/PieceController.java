package GestionaleAziendale.GesionaleBack.controller.machineController;

import GestionaleAziendale.GesionaleBack.dto.dtoMachine.PartsDto;
import GestionaleAziendale.GesionaleBack.dto.dtoMachine.PieceDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.machine.Parts;
import GestionaleAziendale.GesionaleBack.entity.machine.Piece;
import GestionaleAziendale.GesionaleBack.service.serviceMachine.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/machine/piece")
public class PieceController {
    @Autowired
    private PieceService pieceService;
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('MANAGER') ")
    public List<Machine> addPiece(@RequestBody @Validated List<PieceDto> pieceList, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new RuntimeException("Richiesta non valida: " + validation.getAllErrors().stream().map(e -> e.getDefaultMessage()).reduce("", (s1, s2) -> s1 + "\n" + s2));
        }
        return pieceService.addPieceList(pieceList);
    }
    @GetMapping("")
    @PreAuthorize("hasAuthority('MANAGER') ")
    public List<Piece> getAllParts() {
        return pieceService.getAllPiece();
    }

}
