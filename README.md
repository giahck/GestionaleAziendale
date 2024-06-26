<div align="center"><div align="center">
  <img src="https://github-readme-stats.vercel.app/api?username=giahck&hide_title=false&hide_rank=false&show_icons=true&include_all_commits=true&count_private=true&disable_animations=false&theme=merko&locale=en&hide_border=false" height="150" alt="stats graph"  />
  <img src="https://github-readme-stats.vercel.app/api/top-langs?username=gabscognamiglio&locale=en&hide_title=false&layout=compact&card_width=320&langs_count=5&theme=merko&hide_border=false" height="150" alt="languages graph"  />
</div>
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

- **Competenze <-> Utente**: le competenze sono associati a utenti specifiche che definiscono le abilità necessarie per quel ruolo. Questa relazione consente di definire le competenze richieste per svolgere determinate responsabilità.

### Macchine

Le macchine rappresentano le risorse hardware gestite dall'azienda. Ogni macchina è identificata da un ID univoco e ha attributi come modello, produttore e data di acquisto.

### Parti

Le parti sono componenti delle macchine, ciascuna con un proprio ID univoco, nome e descrizione. Ogni parte è associata a una specifica macchina e può avere molteplici pezzi e ticket associati.

### Pezzi (Pieces)

I pezzi sono parti specifiche identificate da un numero di serie e caratterizzate da un materiale specifico. Ogni pezzo è collegato a una parte specifica della macchina.

### Ticket

I ticket rappresentano le richieste di assistenza o manutenzione relative alle parti o alle macchine. Ogni ticket ha un ID univoco, una descrizione del problema e uno stato corrente (aperto, in corso, chiuso, ecc.).

## Relazioni

- **Macchine <-> Parti**: Ogni macchina può avere molte parti, gestite tramite una relazione uno-a-molti.
- **Parti <-> Pezzi**: Ogni parte può essere composta da molti pezzi, stabilita tramite una relazione uno-a-molti.
- **Parti <-> Ticket**: Ogni parte può essere associata a molti ticket, gestita tramite una relazione uno-a-molti.

## Integrazione con API ChatGPT per Gestione dei Ticket

### Interazione dell'Operatore

L'operatore avvia una richiesta specificando la macchina o la parte coinvolta nel problema attraverso l'interfaccia utente del sistema.

### Invio della Richiesta all'API

L'API ChatGPT riceve la richiesta dettagliata, ad esempio: "Come posso risolvere il problema sulla macchina con identificatore [ID]?".

### Elaborazione della Richiesta

L'API elabora la richiesta utilizzando modelli di linguaggio per comprendere il contesto e fornire una risposta pertinente, che potrebbe includere suggerimenti di risoluzione del problema, procedure di manutenzione, possibili cause del guasto, o altro supporto informativo.

### Risposta dell'API

La risposta dell'API ChatGPT viene integrata direttamente nel sistema di gestione dei ticket, associandola al ticket corrispondente. Questo processo permette di tenere traccia delle interazioni e di garantire una risoluzione efficace e tempestiva dei problemi segnalati.

## Note

- Il sistema è progettato per gestire in modo flessibile le risorse hardware dell'organizzazione, facilitando la manutenzione e l'assistenza attraverso l'integrazione avanzata con l'API ChatGPT.
- L'interfaccia utente moderna e user-friendly permette una gestione intuitiva delle macchine, delle parti e dei ticket, migliorando l'efficienza operativa complessiva dell'organizzazione. 
<div align="center">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/javascript/javascript-original.svg" height="30" alt="javascript logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/typescript/typescript-original.svg" height="30" alt="typescript logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/html5/html5-original.svg" height="30" alt="html5 logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/css3/css3-original.svg" height="30" alt="css3 logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/apache/apache-original.svg" height="30" alt="apache logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-plain.svg" height="30" alt="angularjs logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/bootstrap/bootstrap-original.svg" height="30" alt="bootstrap logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" height="30" alt="spring logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/sass/sass-original.svg" height="30" alt="sass logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" height="30" alt="postgresql logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="30" alt="java logo"  />
  <img width="12" />
</div>
</div>
