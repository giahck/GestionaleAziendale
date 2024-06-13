import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { BehaviorSubject, catchError, tap, throwError } from 'rxjs';
import { AuthData } from '../models/authData.interface';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt'; 
import { Register } from '../models/register.interface';
import { Router } from '@angular/router';
import { log } from 'console';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  apiURL = environment.apiURL;
  private authSub = new BehaviorSubject<AuthData | null>(null);
  user$ = this.authSub.asObservable();
  jwtHelper = new JwtHelperService();
  timeOut: any;
  constructor( private http: HttpClient,private router: Router) { }
  register(data: Register) {
    console.log('data: ', data);
    return this.http
      .post(`${this.apiURL}auth/register`, data,{ responseType: 'text' })
      .pipe(catchError(this.errors));
  }
  login(data: { email: string; password: string }) {
    console.log('data: ', data);
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
        this.authSub.next(user);
      } else {
       
        this.router.navigate(['/login']);
      }
    } else {
      console.error('localStorage is not available');
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
