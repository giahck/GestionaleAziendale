export interface Ticket {
    descrizione: string;
    note: string;
    priority: PriorityTicketEnum;
    stato: StatoTicketEnum;
    dataApertura: string; 
    dataChiusura: string | null; 
    dataScadenza: string; 
    dataRisposta: string; 
    dataUltimaModifica: string; 
    partIds: number[];
    userId: number;
  }
  
  export enum PriorityTicketEnum {
    EMERGENZA = 'EMERGENZA',
    URGENZA = 'URGENZA',
    ALTA = 'ALTA',
    MEDIA = 'MEDIA',
    BASSA = 'BASSA'
  }
  export enum StatoTicketEnum {
    APERTO = 'APERTO',
    CHIUSO = 'CHIUSO',
    IN_ATTESA = 'IN_ATTESA',
    RISOLTO = 'RISOLTO',
    NON_RISOLTO = 'NON_RISOLTO'
  }