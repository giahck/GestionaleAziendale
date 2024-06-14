import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { BehaviorSubject, Observable, catchError, map, tap, throwError } from 'rxjs';
import { AuthData } from '../models/authData.interface';
import { HttpClient, HttpParams } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt'; 
import { Register } from '../models/register.interface';
import { Router } from '@angular/router';
import { log } from 'console';
import { Competenze } from '../models/competenze.interface';

interface DataState {
  popupVisible: boolean;
  competenze: boolean;
  id: number | null;
}
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private stateKey = 'authState';
 private state = new BehaviorSubject<DataState>({
    popupVisible: false,
    competenze: false,
    id: null
  }); 
  state$: Observable<DataState> = this.state.asObservable()
   apiURL = environment.apiURL;
  private authSub = new BehaviorSubject<AuthData | null>(null);
  user$ = this.authSub.asObservable();
  jwtHelper = new JwtHelperService();
  timeOut: any;
  constructor( private http: HttpClient,private router: Router) { 
    /* this.state.next(this.getStateFromStorage()); */
  }
  /* private getStateFromStorage(): DataState {
    const storedState = sessionStorage.getItem(this.stateKey);
    return storedState ? JSON.parse(storedState) : {
      popupVisible: false,
      competenze: false,
      id: null
    }; 
  }*/
  saveStateToStorage(state: DataState): void {
    sessionStorage.setItem(this.stateKey, JSON.stringify(state));
  }
  
    // Setter method
    setState(newState: DataState) {
      this.state.next(newState);
      this.saveStateToStorage(newState);
     // console.log('newState: ', newState);
    }
  
    // Getter method (optional)
    getState(): DataState {
      return this.state.getValue();
    }
    competenze(data:Competenze){///competenze/register
      return this.http.post(`${this.apiURL}auth/register`, data, { responseType: 'text' })
    }

  getEmailConfirmedState(): Observable<boolean>{
    const statoCorrente = this.getState();
    const params = new HttpParams().set('id', statoCorrente.id?.toString() || '');
    return this.http.get<boolean>(`${this.apiURL}auth/email-confirmed`, { params })
    .pipe(
   //   map(response => response === 'true'),
      catchError(error => {
        console.error('Error verifying email:', error);
        return throwError(error);
      })
    ); 
  }
  register(data: Register) {
    console.log('data: ', data);
    return this.http
    .post(`${this.apiURL}auth/register`, data, { responseType: 'text' })
    .pipe( map((response) => Number(response)),
      catchError(this.errors)
    );
  }
  login(data: { email: string; password: string }) {
    //console.log('data: ', data);
    return this.http.post<AuthData>(`${this.apiURL}auth/login`, data ).pipe(
      tap(async(data) => {
        console.log('Auth data: ', data);
      }),
      tap((data) => {
        this.authSub.next(data);
        localStorage.setItem('user', JSON.stringify(data));
        this.autoLogout(data);
      }), 
      catchError(this.errors)
    );
  }
  restore() {
    if (typeof localStorage !== 'undefined') {
      const userLocalStorage = localStorage.getItem('user');
      if (userLocalStorage) {
        const user = JSON.parse(userLocalStorage);
        const token = user.accessToken; // Adjust this based on your actual structure
       
        if (token) {
          const isExpired = this.jwtHelper.isTokenExpired(token);
          if (!isExpired) {
            // Token is valid
          //  console.log('token: ', token);
            this.authSub.next(user);
          } else {
            // Token is expired
            localStorage.removeItem('user');
            this.router.navigate(['/login']);
          }
        } else {
          // No token found
          localStorage.removeItem('user');
          this.router.navigate(['/login']);
        }
      } else {
        // No user found in localStorage
        this.router.navigate(['/login']);
      }
    } else {
     // console.error('localStorage is not available');
    }
  }

  autoLogout(user: AuthData) {
    const dateExpiration = this.jwtHelper.getTokenExpirationDate(user.accessToken) as Date;
    const millisecondsExp = dateExpiration.getTime() - new Date().getTime();
    this.timeOut = setTimeout(() => {
        this.logout();
    }, millisecondsExp);
}
  private errors(err: any) {
    console.log(err.error);
    switch (err.error) {
      case 'Email already exists':
        return throwError('The user already exists.');
        break;

      case 'Incorrect password':
        return throwError('Incorrect password');
        break;

      case 'Cannot find user':
        return throwError('User not found');
        break;

      default:
        return throwError('Request error');
        break;
    }
  }
  logout() {
    this.authSub.next(null);
    localStorage.removeItem('user');
    this.router.navigate(['/login']);
    if (this.timeOut) {
      clearTimeout(this.timeOut);
    }
  }
}
function jwt_decode(token: any) {
  throw new Error('Function not implemented.');
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