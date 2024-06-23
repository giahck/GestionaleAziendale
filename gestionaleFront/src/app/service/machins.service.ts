import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Machine } from '../models/machin/machine.interface';
import { BehaviorSubject, Observable, catchError, of, tap, throwError } from 'rxjs';
import { log } from 'console';

@Injectable({
  providedIn: 'root'
})
export class MachinsService {
  private machinesSubject = new BehaviorSubject<Machine[]>([]);
  machines$: Observable<Machine[]> = this.machinesSubject.asObservable();
  private machineLoaded = false;
  apiUrl=environment.apiURL;
  constructor(private http:HttpClient) { }

  getMachines$(): Observable<Machine[]> {
    return this.machines$;
  }

  setMachines(machines: Machine[]): void {
    this.machinesSubject.next(machines);
  }


  getMachines(): void {
   /*  if (this.machineLoaded) {
      return;
    } */
    this.http.get<Machine[]>(`${this.apiUrl}machine`).pipe(
      catchError(error => {
        this.handleError(error);
        return of([]); 
      }),
      tap((machines: Machine[]) => {
        this.setMachines(machines);
        this.machineLoaded = true;
      })
    ).subscribe();
  }
  postMaschine(machine: FormData): Observable<Machine> {
    return this.http.post<Machine>(`${this.apiUrl}machine/add`, machine).pipe(
      catchError(this.handleError)
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
