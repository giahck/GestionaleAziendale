export enum StatoMaschineEnum {
    ATTIVA = 'ATTIVA',
    INATTIVA = 'INATTIVA',
    GUASTA = 'GUASTA',
    MANUTENZIONE = 'MANUTENZIONE'
  }
  
  export interface Machine {
    id: number;
    nomeMacchina: string;
    marca: string;
    modello: string;
    matricola: string;
    dataAcquisto: Date;
    statoMaschine: StatoMaschineEnum; // ATTIVA, INATTIVA, GUASTA, MANUTENZIONE
    description: string;
    partsId?: number[];
    competenzaId?: number;
    parts: Part[];
    photo?: string | null;
}

export interface Part {
  machine?: Machine;
  id: number;
  nomeParte: string;
  descrizione: string;
  note: string;
  pieces: Piece[];
  quantityParts: number;
}

export interface Piece {
  id: number;
  nomePezzo: string;
  descrizione: string;
  seriale: number;
  materiale: string;
  quantityPiece: number;
}