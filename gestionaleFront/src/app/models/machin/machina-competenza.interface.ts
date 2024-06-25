export interface MachinaCompetenza {
    id: number;
    nomeMacchina: string;
    marca: string;
    modello: string;
    description: string;
    competenza?: Competenza | null;
  }
  
  interface Competenza {
    idCompetenza: number;
    nomeCompetenza: string;
    descrizione: string;
    livello: number;
  }
