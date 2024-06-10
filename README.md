Relazione tra Entità Users, Ruolo e Competenza
Descrizione
Questo progetto gestisce le entità Users, Ruolo e Competenza per gestire gli utenti, i ruoli e le competenze all'interno di un sistema di gestione aziendale.

Entità
Users
L'entità Users rappresenta gli utenti del sistema. Ogni utente ha un'email, un nome, un cognome, una data di nascita, un codice fiscale e altri attributi personali. Gli utenti possono avere uno o più ruoli.

Ruolo
L'entità Ruolo rappresenta i ruoli disponibili nel sistema. Ogni ruolo ha un nome, una descrizione e un livello di importanza. I ruoli possono avere competenze predefinite associate a loro.

Competenza
L'entità Competenza rappresenta le competenze disponibili nel sistema. Ogni competenza ha un nome, una descrizione e un livello di competenza. Le competenze possono essere associate a uno o più ruoli.

Relazioni
Utente <-> Ruolo: Relazione many-to-many tra gli utenti e i ruoli. Ogni utente può avere uno o più ruoli e ogni ruolo può essere associato a uno o più utenti.

Ruolo <-> Competenza: Relazione many-to-many tra i ruoli e le competenze. Ogni ruolo può avere competenze predefinite associate ad esso e ogni competenza può essere associata a uno o più ruoli.

Note
Gli utenti possono avere uno o più ruoli all'interno del sistema.
I ruoli possono avere competenze predefinite associate a loro per definire le responsabilità e le competenze necessarie per quel ruolo.
Le competenze possono essere condivise tra più ruoli o essere specifiche per un singolo ruolo.
