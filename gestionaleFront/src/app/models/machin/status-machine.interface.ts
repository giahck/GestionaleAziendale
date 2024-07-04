import { StatoMaschineEnum } from "./machine.interface";

export interface StatusMachine {
    description: string;
    matricola: string;
    photo: string;
    nomeMacchina: string;
    marca: string;
    modello: string;
    statoMaschine: StatoMaschineEnum;
    pdfContent: string;
}
