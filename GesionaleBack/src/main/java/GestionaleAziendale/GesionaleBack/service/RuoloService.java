package GestionaleAziendale.GesionaleBack.service;


import GestionaleAziendale.GesionaleBack.dto.RuoloDto;
import GestionaleAziendale.GesionaleBack.maperDto.RuoloMapper;
import GestionaleAziendale.GesionaleBack.repository.RuoloRepository;
import GestionaleAziendale.GesionaleBack.entity.utenti.Ruolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuoloService {

    @Autowired
    private RuoloRepository ruoloRepository;

    @Autowired
    private RuoloMapper ruoloMapper;

    public Ruolo findById(int id) {
        return ruoloRepository.findById(id).orElse(null);
    }
    public RuoloDto saveRuolo(RuoloDto ruoloDto) {
        Ruolo ruolo = ruoloMapper.toEntity(ruoloDto);
        Ruolo savedRuolo = ruoloRepository.save(ruolo);
        return ruoloMapper.toDto(savedRuolo);
    }
}
