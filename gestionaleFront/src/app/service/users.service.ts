import { BehaviorSubject, Observable, Subject, throwError } from 'rxjs';
import { UserDati } from './../models/user-dati.interface';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { get } from 'http';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private UserDatiSubject = new BehaviorSubject<UserDati[]>([]);
  UserDati$ = this.UserDatiSubject.asObservable();
  private UserDatiLoaded = false;
  apiUrl = environment.apiURL;
  constructor(private http: HttpClient) {}
  getUserIdFromLocalStorage(): any {
    const userId = localStorage.getItem('user');
    if (!userId) {
      console.error('UserID non trovato nel localStorage');
      return null;
    }
    try {
      const user = JSON.parse(userId);
      console.log('UserID trovato nel localStorage:', user);
      return user;
    } catch (error) {
      console.error('Errore nel parsing dell\'userID dal localStorage', error);
      return null;
    }
  }
  getUserDati$(): Observable<UserDati[]> {
    if (!this.UserDatiLoaded) {
      this.getUserDati();
    }
    return this.UserDati$;
  }
  setUserDati(UserDati: UserDati[]): void {
    this.UserDatiSubject.next(UserDati);
  }
  getUserDati(): void {
    if (this.UserDatiLoaded) {
      return;
    }
   // console.log('Chiamata API per ottenere i dati degli utenti');
    this.http.get<UserDati[]>(`${this.apiUrl}users`).subscribe(
      (UserDati: UserDati[]) => {
        this.setUserDati(UserDati), (this.UserDatiLoaded = true);
      },
      (error) => this.handleError(error) // Gestione dell'errore utilizzando handleError
    );
  }
  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof Error || (error.error && "message" in error.error)) {
      // Errore del client-side o di rete
      console.error('Errore:', error.error.message);
    } else {
      // Errore dal lato del server
      console.error(
        `Codice Errore dal lato server ${error.status}, ` +
        `messaggio di errore: ${error.message}`
      );
    }
    // Ritorna un observable con un messaggio di errore utile per il consumatore del servizio
    return throwError('Qualcosa è andato storto; riprova più tardi.');
  }
}
