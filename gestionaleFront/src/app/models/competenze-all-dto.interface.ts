export interface CompetenzeAllDto {
    idCompetenza: number;
    nomeCompetenza: string;
    descrizione: string;
    livello: number;
    machine: MachineDTO;
    users: UserDtoCompetenze[]; 
  }
  
  export interface MachineDTO {
    nomeMacchina: string;
    modello: string;
  }
  export interface UserDtoCompetenze {
    id: number;
    nome: string;
    cognome: string;
  }
