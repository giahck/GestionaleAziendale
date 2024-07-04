import { StatusMachine } from './../models/machin/status-machine.interface';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, of, throwError, tap } from 'rxjs';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class StatusMachineService {
  apiUrl=environment.apiURL;
  stompClient: any;
  topic: string = '/topic/machineStatus';
  private StatusMachineSubject = new BehaviorSubject<StatusMachine[]>([]);
  statusMachine$ = this.StatusMachineSubject.asObservable();
  private statusMachineLoaded = false;
  private isConnected: boolean = false;
  constructor(private http:HttpClient) { }
  getStatusMachine$(): Observable<StatusMachine[]> {
    if (!this.statusMachineLoaded) {
      this.getMachineStatus();
    }
    return this.statusMachine$;
  }
  setStatusMachine(statusMachine: StatusMachine[]): void {
  //  console.log("StatusMachineService.setStatusMachine:", statusMachine);
    this.StatusMachineSubject.next(statusMachine);
  }

  


  Connect() {
    console.log("Tentativo di connessione a WebSocket con URL:", `${this.apiUrl}machineStatus`);
    const socket = new SockJS(`${this.apiUrl}machineStatus`);
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, (frame:any) => {
    //  console.log("Connesso al WebSocket:", frame);
      this.isConnected = true;
      this.stompClient.subscribe(this.topic, (message:any) => {
       // console.log("Messaggio ricevuto:",);
        if (message.body) {
         // console.log("Messaggio ricevuto:", message.body);
          const parsedMessage = JSON.parse(message.body) as StatusMachine[];
          this.setStatusMachine(parsedMessage);
       //   console.log("Messaggio ricevuto:", parsedMessage);
        } 
      }, (error:any) => {
        console.error("Errore nella sottoscrizione:", error);
      });
    }, (error:any) => {
      this.isConnected = false;
      console.error("Errore nella connessione WebSocket:", error);
    });
    
  }
  Disconnect() {
    if (!this.isConnected) {
      console.log("Non connesso al WebSocket. Nessuna azione di disconnessione necessaria.");
      return;
    }

    // Chiudi la connessione qui e imposta isConnected su false
    if (this.stompClient) {
      this.stompClient.disconnect(() => {
     //   console.log("Disconnesso dal WebSocket.");
        this.isConnected = false;
      });
    }
  }



  


  getMachineStatus(){
     this.http.get<StatusMachine[]>(`${this.apiUrl}machine/status`).pipe(
      tap((statusMachine: StatusMachine[]) => {
        this.setStatusMachine(statusMachine), (this.statusMachineLoaded = true);
      }),
      catchError(error => {
        this.handleError(error);
        return of([]); 
      })
    ).subscribe();
  }
  
  
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Si è verificato un errore sconosciuto!';
    if (error.error instanceof Error) {
      // Errore del client-side o di rete
      console.error('Errore:', error.error.message);
      errorMessage = `Errore del client-side o di rete: ${error.error.message}`;
    } else {
      // Errore dal lato del server
      console.error(
        `Codice Errore dal lato server ${error.status}, ` +
        `messaggio di errore: ${error.message}`
      );
      errorMessage = `Errore dal lato server: ${error.status}, messaggio di errore: ${error.message}`;
    }
    // Ritorna un observable con un messaggio di errore utile per il consumatore del servizio
    return throwError(errorMessage);
  }

}
