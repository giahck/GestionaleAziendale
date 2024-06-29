package GestionaleAziendale.GesionaleBack.service.serviceMachine;

import GestionaleAziendale.GesionaleBack.dto.dtoMachine.PartsDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.machine.Parts;
import GestionaleAziendale.GesionaleBack.maperDto.mapperDtoMachine.PartsMapper;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.MachineRepository;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartsService {
    @Autowired
    private PartsRepository partsRepository;
    @Autowired
    private PartsMapper partsMapper;
    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private GenericMachineService genericMachineService;
    public List<Machine> addPartsList(List<PartsDto> partsList) {
        /*List<Parts> addedParts = new ArrayList<>();*/
        for (PartsDto partsDto : partsList) {
            if (partsDto == null)
                throw new IllegalArgumentException("PartDto is null");
            Parts parts = partsMapper.partsDtoToParts(partsDto);

            Optional<Machine> optionalMachine = machineRepository.findById(partsDto.getMachineId());
            if (!optionalMachine.isPresent()) {
                System.out.println("Machine with id " + partsDto.getMachineId() + " not found");
                continue;
            }

            parts.setMachine(optionalMachine.get());
          /*  addedParts.add(partsRepository.save(parts));*/
            partsRepository.save(parts);
        }

        return genericMachineService.getAllMachinesAndSubclasses();
    }
    public List<Parts> getAllParts() {
        return partsRepository.findAll();
    }
    public Parts getPartsById(int id) {
        return partsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Parts with id " + id + " not found"));
    }


}
