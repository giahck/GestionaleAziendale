package GestionaleAziendale.GesionaleBack.enums;

public enum RuoloEnum {
    ADMIN,//ACCESSO A TUTTO TRANE AI DATI DEL USER
    DIPENDENTE,//DIPENDE DALLA COMPETENZA
    MANAGER,//ACCESSO AL USER
    DIRETTORE,//ACCESSO A TUTTO PRIVILEGGI MASSIMI
    IT,//informatico o sistemista ACCESSO A TUTTO TRANE AI DATI DEL USER
    USER,//ACCESSO AI PROPRI DATI
    OPERATORE_TECNICO,//ACCESSO AI PROPRI DATI E AI TIKET E MACHINA DEL TIKET
}
