import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import {
  BehaviorSubject,
  Observable,
  catchError,
  map,
  tap,
  throwError,
} from 'rxjs';
import { AuthData } from '../models/authData.interface';
import { HttpClient, HttpParams } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Register } from '../models/register.interface';
import { Router } from '@angular/router';
import { Competenze } from '../models/competenze.interface';
import { StatoRegister } from '../models/stato-register.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  //private stateKey = 'authState';
  private state = new BehaviorSubject<StatoRegister>({
    popupVisible: false,
    competenze: false,
    id: null,
  });
  state$: Observable<StatoRegister> = this.state.asObservable();
  apiURL = environment.apiURL;
  private authSub = new BehaviorSubject<AuthData | null>(null);
  user$ = this.authSub.asObservable();
  jwtHelper = new JwtHelperService();
  timeOut: any;
  constructor(private http: HttpClient, private router: Router) {}

  // Setter method
  setState(newState: StatoRegister) {
    console.log('New state:', newState);
    this.state.next(newState);
  }

  // Getter method
  getState(): StatoRegister {
    return this.state.getValue();
  }
  competenze(data: Competenze) {
    ///competenze/register
    return this.http.post(`${this.apiURL}auth/competenze`, data, {
      responseType: 'text',
    });
  }

  getEmailConfirmedState(registerForm: Register): Observable<boolean> {
    const statoCorrente = this.getState();
    const params = new HttpParams().set(
      'id',
      statoCorrente.id?.toString() || ''
    );

    return this.http
      .get<boolean>(`${this.apiURL}auth/email-confirmed`, { params })
      .pipe(
        tap((response: boolean) => {
          if (response === true) {
            // Email confirmed, proceed with login
            this.login({
              email: registerForm.email,
              password: registerForm.password,
              rememberMe: false,
            }).subscribe();
          }
        }),
        catchError((error) => {
          console.error('Error verifying email:', error);
          return throwError(error);
        })
      );
  }
  register(data: Register) {
    console.log('data: ', data);
    return this.http
      .post(`${this.apiURL}auth/register`, data, { responseType: 'text' })
      .pipe(
        map((response) => Number(response)),
        catchError(this.handleError)
      );
  }
  login(data: { email: string; password: string; rememberMe: boolean }): Observable<AuthData> {
    return this.http.post<AuthData>(`${this.apiURL}auth/login`, data).pipe(
      tap(async (dataResponse) => {
        if (dataResponse && dataResponse.accessToken) {
          if (data.rememberMe) {
            localStorage.setItem('token', dataResponse.accessToken);
          } else {
            sessionStorage.setItem('token', dataResponse.accessToken);
          }
          localStorage.setItem('user', JSON.stringify(dataResponse));
          this.autoLogout(dataResponse);
        }
        this.authSub.next(dataResponse);
      }),
      catchError(this.handleError)
    );
  }
  isLoggedIn(): boolean {
    return !!localStorage.getItem('token') || !!sessionStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token') || sessionStorage.getItem('token');
  }
  restore() {
    if (typeof localStorage !== 'undefined') {
      // Check for 'user' in localStorage
      let userLocalStorage = localStorage.getItem('user');
      let token = localStorage.getItem('token');
  
      if (userLocalStorage) {
        // If 'user' exists, parse it and get the token from it
        const user = JSON.parse(userLocalStorage);
        if (user.accessToken) {
          token = user.accessToken;
        }
      }
  
      if (token) {
        // Validate the token
        const isExpired = this.jwtHelper.isTokenExpired(token);
        if (!isExpired) {
          // Token is valid
          const user = userLocalStorage ? JSON.parse(userLocalStorage) : { accessToken: token };
          this.authSub.next(user);
        } else {
          // Token is expired, clear localStorage and navigate to login
          localStorage.removeItem('user');
          localStorage.removeItem('token');
          this.router.navigate(['/login']);
        }
      } else {
        // No token found, clear localStorage and navigate to login
        localStorage.removeItem('user');
        localStorage.removeItem('token');
        this.router.navigate(['/login']);
      }
    } else {
      // localStorage is not available
      console.error('localStorage is not available');
    }
  }

  autoLogout(user: AuthData) {
    const dateExpiration = this.jwtHelper.getTokenExpirationDate(
      user.accessToken
    ) as Date;
    const millisecondsExp = dateExpiration.getTime() - new Date().getTime();
    this.timeOut = setTimeout(() => {
      this.logout();
    }, millisecondsExp);
  }

  logout() {
    this.authSub.next(null);
    localStorage.removeItem('user');
    localStorage.removeItem('token');
    sessionStorage.removeItem('token');
    this.router.navigate(['/login']);
    if (this.timeOut) {
      clearTimeout(this.timeOut);
    }
  }
    // Metodo per gestire gli errori delle richieste HTTP
    private handleError(error: any): Observable<never> {
      console.error('Errore durante la richiesta:', error);
      let errorMessage = 'Si è verificato un errore.';
      if (error.error instanceof ErrorEvent) {
        // Errore del client
        errorMessage = `Errore: ${error.error.message}`;
      } else {
        // Errore del server
        errorMessage = `Errore ${error.status}: ${error.message}`;
      }
      return throwError(errorMessage);
    }
}

/* // Funzione per verificare l'email utilizzando un token di verifica
verifyEmail(token: string): Observable<any> {
  return this.http.get(`${this.apiURL}auth/registrationConfirm?token=${token}`,{ responseType: 'text' })
  .pipe(
    tap(response => {
      // Gestisci qui la risposta, ad esempio controlla se è un messaggio di conferma
      console.log('Response from server:', response);
      if (response === 'Account verified successfully.') {
        // Esegui le operazioni necessarie dopo la conferma dell'email
        this.emailConfirmedSubject.next(true);
      } else {
        console.error('Unexpected response from server:', response);
      }
    }),
    catchError(error => {
      console.error('Error verifying email:', error);
      return throwError(error);
    })
  );
}

  // Metodo per confermare l'email e aggiornare lo stato
  confirmEmail(): void {
    // Esegui qui la logica di conferma dell'email

    // Dopo la conferma, aggiorna lo stato
    this.emailConfirmedSubject.next(true);
  
  }

  // Metodo per ottenere lo stato di conferma dell'email come Observable
  getEmailConfirmedState(): Observable<boolean> {
    return this.emailConfirmedSubject.asObservable();
  } */
