package GestionaleAziendale.GesionaleBack.service.serviceMachine;

import GestionaleAziendale.GesionaleBack.dto.dtoMachine.PieceDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Machine;
import GestionaleAziendale.GesionaleBack.entity.machine.Parts;
import GestionaleAziendale.GesionaleBack.entity.machine.Piece;
import GestionaleAziendale.GesionaleBack.maperDto.mapperDtoMachine.PieceMapper;
import GestionaleAziendale.GesionaleBack.repository.machineRepository.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PieceService {
    @Autowired
    private PieceRepository pieceRepository;
    @Autowired
    private PieceMapper pieceMapper;
    @Autowired
    private PartsService partsService;
    @Autowired
    private GenericMachineService genericMachineService;
    public List<Machine> addPieceList(List<PieceDto> pieceList) {
        List<Piece> addedPiece = new ArrayList<>();
        for (PieceDto pieceDto : pieceList) {
            if (pieceDto == null)
                throw new IllegalArgumentException("PieceDto is null");
            Piece piece = pieceMapper.pieceDtoToPiece(pieceDto);

            Parts parts = partsService.getPartsById(pieceDto.getPartsId());
            piece.setParts(parts);
            addedPiece.add(pieceRepository.save(piece));
        }
        /*return addedPiece;*/
        return genericMachineService.getAllMachinesAndSubclasses();
    }
    public List<Piece> getAllPiece() {
        return pieceRepository.findAll();
    }
    public Piece getPieceById(int id) {
        return pieceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Piece with id " + id + " not found"));
    }



}
