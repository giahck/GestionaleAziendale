package GestionaleAziendale.GesionaleBack.service.serviceMachine;

import GestionaleAziendale.GesionaleBack.dto.dtoMachine.GenericMachineDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.machine.Parts;
import GestionaleAziendale.GesionaleBack.entity.machine.genericMachine.MachineGeneric;
import GestionaleAziendale.GesionaleBack.maperDto.mapperDtoMachine.MachineGenericMapper;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.MachineGenericRepository;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.MachineRepository;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenericMachineService {
    @Autowired
    private MachineGenericMapper machineGenericMapper;
    @Autowired
    private MachineGenericRepository machineGenericRepository;
    @Autowired
    private PartsRepository partsRepository;
    @Autowired
    private MachineRepository machineRepository;

    public Machine addMachine(GenericMachineDto genericMachineDto) {
        MachineGeneric machineGeneric = machineGenericMapper.genericDtoToMachine(genericMachineDto);
        if (genericMachineDto.getPartsId() != null && !genericMachineDto.getPartsId().isEmpty()) {
            machineGeneric.setParts(genericMachineDto.getPartsId().stream().map(id -> {
                Parts parts = partsRepository.findById(id).orElse(null);
                if (parts == null) {
                    System.out.println("Part not found");
                }
                return parts;
            }).collect(Collectors.toList()));
        }
        return machineGenericRepository.save(machineGeneric);
    }
    public List<Machine> getAllMachine() {
        return machineRepository.findAll();
    }

}
