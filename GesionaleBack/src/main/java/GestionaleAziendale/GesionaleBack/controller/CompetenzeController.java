package GestionaleAziendale.GesionaleBack.controller;

import GestionaleAziendale.GesionaleBack.dto.CompetenzeRegDto;
import GestionaleAziendale.GesionaleBack.service.CompetenzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/competenze")
public class CompetenzeController {
    @Autowired
    private CompetenzeService competenzeService;
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('DIPENDENTE','DIRETTORE','USER','MANAGER','ADMIN','IT')")
    public CompetenzeRegDto saveCompetenze(@RequestBody @Validated CompetenzeRegDto competenzeDto, BindingResult bindingResult) {
        System.out.println(competenzeDto);
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("Richiesta non valida: " + bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).reduce("", (s1, s2) -> s1 + "\n" + s2));
        }
        return competenzeService.saveCompetenza(competenzeDto);
    }

}
