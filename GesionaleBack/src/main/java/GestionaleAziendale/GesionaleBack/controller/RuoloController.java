package GestionaleAziendale.GesionaleBack.controller;

import GestionaleAziendale.GesionaleBack.dto.RuoloDto;
import GestionaleAziendale.GesionaleBack.exeptions.BadRequestException;
import GestionaleAziendale.GesionaleBack.service.RuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ruolo")
public class RuoloController {

    @Autowired
    private RuoloService ruoloService;

    @PostMapping
    @PreAuthorize("hasAuthority('IT')")
    public ResponseEntity<RuoloDto> saveRuolo(@RequestBody @Validated RuoloDto ruoloDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Richiesta non valida: " + bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).reduce("", (s1, s2) -> s1 + "\n" + s2));
        }
        RuoloDto savedRuoloDto = ruoloService.saveRuolo(ruoloDto);
        return new ResponseEntity<>(savedRuoloDto, HttpStatus.CREATED);
    }
}
