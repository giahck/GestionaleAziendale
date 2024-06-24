import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, of, tap, throwError } from 'rxjs';
import { Machine, Part } from '../models/machin/machine.interface';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { MachinsService } from './machins.service';
import { MaschineComponent } from '../components/maschine/maschine.component';
import { machine } from 'node:os';

@Injectable({
  providedIn: 'root'
})
export class PezziEpartiService {
  private partiSubject = new BehaviorSubject<Part[]>([]);
  parti$: Observable<Part[]> = this.partiSubject.asObservable();
  apiUrl=environment.apiURL;
  constructor(private http:HttpClient, private machinSrv:MachinsService) { }

  getParti$(): Observable<Part[]> {
    return this.parti$;
  }

  setParti(parti: Part[]): void {
    this.partiSubject.next(parti);
  }

  getParti(): void {
   
     this.http.get<Part[]>(`${this.apiUrl}machine/parts`).pipe(
       catchError(error => {
         this.handleError(error);
         return of([]); 
       }),
       tap((partis: Part[]) => {
         this.setParti(partis);
       
       })
     ).subscribe();
   }
   postParti(parti: Part[]): Observable<Machine[]> {
     return this.http.post<Machine[]>(`${this.apiUrl}machine/parts/add`, parti).pipe(
       catchError(this.handleError),
       tap((machine: Machine[]) => {
         this.machinSrv.setMachines(machine);
        })
     );
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
