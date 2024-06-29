package GestionaleAziendale.GesionaleBack.maperDto.mapperDtoMachine;

import GestionaleAziendale.GesionaleBack.dto.dtoMachine.PieceDto;
import GestionaleAziendale.GesionaleBack.entity.machine.Piece;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PieceMapper {
    PieceDto pieceToPieceDto(Piece piece);
    Piece pieceDtoToPiece(PieceDto pieceDto);
}
