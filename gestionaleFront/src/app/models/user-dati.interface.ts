export interface UserDati {
  id: number;
    email: string;
    cognome: string;
    nome: string;
    sesso: string;
    dataDiNascita: Date;
    comuneDiNascita: string;
    codiceFiscale: string;
    telefono: number;
    indirizzo: string;
    ruolo: Role[];
    competenze: Competenza[];
}
export interface Role {
    idRuolo: number;
    nomeRuolo: string;
    descrizione: string;
    livello: number;
  }
  
  export interface Competenza {
    idCompetenza: number;
    nomeCompetenza: string;
    descrizione: string;
    livello: number;
    machine: number | null;
    machineId: number | null;
  }
