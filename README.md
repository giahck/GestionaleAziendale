# Sistema di Gestione Aziendale

## Descrizione

Questo progetto implementa un sistema di gestione aziendale per gestire gli utenti, i ruoli e le competenze all'interno di un'organizzazione. Utilizza tecnologie moderne per garantire un'esperienza utente efficiente e intuitiva.

## Entità

### Utenti

Gli utenti sono la colonna portante del sistema. Ogni utente ha un'email, un nome, un cognome, una data di nascita e altre informazioni personali. Possono essere assegnati a uno o più ruoli all'interno dell'organizzazione.

### Ruoli

I ruoli definiscono le responsabilità e le competenze all'interno dell'organizzazione. Ogni ruolo ha un nome, una descrizione e un livello di importanza. Sono associati a competenze specifiche che definiscono le abilità necessarie per quel ruolo.

### Competenze

Le competenze rappresentano le abilità e le conoscenze necessarie per svolgere determinati compiti. Ogni competenza ha un nome, una descrizione e un livello di competenza. Possono essere associate a uno o più ruoli per definire le competenze richieste per quel ruolo.

## Relazioni

- **Utente <-> Ruolo**: Gli utenti possono essere assegnati a uno o più ruoli all'interno dell'organizzazione. Questa relazione consente di definire le responsabilità di ciascun utente.

- **Ruolo <-> Competenza**: I ruoli sono associati a competenze specifiche che definiscono le abilità necessarie per quel ruolo. Questa relazione consente di definire le competenze richieste per svolgere determinate responsabilità.

## Note

- Il sistema è progettato per essere flessibile e adattabile alle esigenze specifiche dell'organizzazione.
- La gestione degli utenti, dei ruoli e delle competenze avviene in modo efficiente e intuitivo attraverso un'interfaccia utente moderna e user-friendly.

