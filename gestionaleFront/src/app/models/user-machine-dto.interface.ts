export interface UserMachineDto {
    userId: number;
    description: string;
    photo: string;
    machines: MachineDto[];
}

export interface MachineDto {
    machineId: number;
    nomeMacchina: string;
    marca: string;
    statoMacchina: string;
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