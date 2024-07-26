<div align="center"><div align="center">
  <img src="https://github-readme-stats.vercel.app/api?username=giahck&hide_title=false&hide_rank=false&show_icons=true&include_all_commits=true&count_private=true&disable_animations=false&theme=merko&locale=en&hide_border=false" height="150" alt="stats graph"  />
  <img src="https://github-readme-stats.vercel.app/api/top-langs?username=gabscognamiglio&locale=en&hide_title=false&layout=compact&card_width=320&langs_count=5&theme=merko&hide_border=false" height="150" alt="languages graph"  />
</div>
  
# Sistema di Gestione Aziendale

**Capstone Project come conseguimento della fine del corso di Full Stack Development con Epicode - Corso intensivo di 6 mesi**

## Descrizione

Il sistema implementato ha due livelli principali. Il primo livello è un sistema di messaggistica con l'integrazione dell'intelligenza artificiale, con una connessione bidirezionale (WebSocket). Il secondo livello è un sistema di apertura dei ticket, con una sezione dedicata al caricamento delle macchine e al tracciamento bidirezionale dello stato delle macchine. Questo permette di tenere traccia degli stati delle macchine (intese come sistemi industriali). Le macchine sono suddivise in tre sezioni: macchina effettiva, parti e pezzi delle parti. Inoltre, il sistema include un sistema interno per la gestione dei ruoli (dipendenti, operatori, ecc.).

## Entità

### Utenti

Gli utenti sono la colonna portante del sistema. Ogni utente ha un'email, un nome, un cognome, una data di nascita e altre informazioni personali. Possono essere assegnati a uno o più ruoli all'interno dell'organizzazione.

### Ruoli

I ruoli definiscono le responsabilità e le competenze all'interno dell'organizzazione. Ogni ruolo ha un nome, una descrizione e un livello di importanza.

### Competenze

Le competenze rappresentano le abilità e le conoscenze necessarie per svolgere determinati compiti. Ogni competenza ha un nome, una descrizione e un livello di competenza. Le competenze fanno da collegamento tra le macchine e gli utenti.

### Macchine

Le macchine rappresentano le risorse hardware gestite dall'azienda. Ogni macchina è identificata da un ID univoco e ha attributi come modello, produttore e data di acquisto.

### Parti

Le parti sono componenti delle macchine, ciascuna con un proprio ID univoco, nome e descrizione. Ogni parte è associata a una specifica macchina e può avere molteplici pezzi e ticket associati.

### Pezzi

I pezzi sono parti specifiche identificate da un numero di serie e caratterizzate da un materiale specifico. Ogni pezzo è collegato a una parte specifica della macchina.

### Ticket

I ticket rappresentano le richieste di assistenza o manutenzione relative alle parti o alle macchine. Ogni ticket ha un ID univoco, una descrizione del problema e uno stato corrente (aperto, in corso, chiuso, ecc.).

## Integrazione con API ChatGPT per Gestione dei Ticket

### Interazione dell'Operatore

L'operatore avvia una richiesta specificando la macchina o la parte coinvolta nel problema attraverso l'interfaccia utente del sistema, un sistema di messaggistica che permette di auto-compilare il form per ottenere una risposta il più precisa possibile dall'API.

### Elaborazione della Richiesta

L'API elabora la richiesta utilizzando modelli di linguaggio per comprendere il contesto e fornire una risposta pertinente, che potrebbe includere suggerimenti di risoluzione del problema, procedure di manutenzione, possibili cause del guasto, o altro supporto informativo.

### Risposta dell'API

La risposta dell'API ChatGPT viene integrata direttamente nel sistema di gestione dei ticket, associandola al ticket corrispondente. Questo processo permette di tenere traccia delle interazioni e di garantire una risoluzione efficace e tempestiva dei problemi segnalati.

## Funzionalità di Tracciamento delle Macchine

Il sistema ha una connessione bidirezionale con le macchine e una sezione dedicata che aggiorna lo stato delle macchine ad ogni cambiamento. Alla creazione di un ticket, il sistema genera un PDF per tutte le macchine e lo ritorna all'utente. Il sistema visualizza solo le macchine assegnate all'utente specifico (se sei un dipendente vedi solo le macchine su cui lavori, se sei un amministratore le vedi tutte).

## Inserimento di Macchine e Competenze

Il sistema include una sezione completa per l'inserimento delle macchine e delle competenze, mettendo in relazione utenti, macchine e competenze.

## Note

Il sistema è stato scritto in forma sperimentale, utilizzando diverse tecnologie come Hibernate, JPA, query native, template, WebSocket, ecc., per rispondere a diverse domande. Ad esempio:

- Come posso aprire una connessione che rimanga attiva?
- Come posso non far vedere le informazioni alle altre persone?
- Come posso sfruttare una determinata classe con PostgreSQL, quindi tramite query?
- Come posso sfruttare al meglio le chiamate HTTP senza farne 3000 inutili?

Ci sono anche soluzioni di prova, come tirare su tutte le informazioni della macchina partendo dall'utente senza creare ulteriori relazioni tra di loro. Quindi, sì, mi sono complicato la vita la maggior parte delle volte, ma è stato fatto tutto a scopo di apprendimento! Quindi ci sono soluzioni buone ma anche molto pesanti. Ho voluto valutare anche dal lato front-end come potesse reagire in determinati contesti passando tanti dati.
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
