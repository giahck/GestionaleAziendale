export interface UserMachineDto {
    userId: number;
    machines: MachineDto[];
  }
  
  export interface MachineDto {
    machineId: number;
    nomeMacchina: string;
    marca: string;
    statoMacchina: string;
    description: string;
    photo: string;
    parts: PartDto[];
  }
  
  export interface PartDto {
    partId: number;
    nomeParte: string;
    descrizione: string;
    note: string;
    quantityParts: number;
    pieces: PieceDto[];
  }
  
  export interface PieceDto {
    pieceId: number;
    nomePezzo: string;
    quantityPiece: number;
    descrizione: string;
  }