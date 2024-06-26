
    export interface AuthData {
        id: number;
        accessToken: string;
        email: string;
        cognome: string;
        nome: string;
        sesso: string;
        dataDiNascita: string;
        comuneDiNascita: string;
        codiceFiscale: string;
        telefono: number;
        indirizzo: string;
        cap: string;
        provincia: string;
        password: string;
        rememberMe: boolean;
        ruoloId?: number[];
      }

