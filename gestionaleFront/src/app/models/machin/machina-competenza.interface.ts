export interface MachinaCompetenza {
    id: number;
    nomeMacchina: string;
    marca: string;
    modello: string;
    description: string;
    competenza?: Competenza | null;
    
    utenti?: UserNameDto[];
}

export interface UserNameDto {
  id: number;
  nome: string;
  cognome: string;
  }
  
  interface Competenza {
    idCompetenza: number;
    nomeCompetenza: string;
    descrizione: string;
    livello: number;
  }
