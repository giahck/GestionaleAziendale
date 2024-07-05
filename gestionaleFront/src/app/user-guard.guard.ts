import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UsersService } from './service/users.service';


@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {
  constructor(private userSrv: UsersService, private router: Router) {}

  canActivate(  route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree{
      const requiredRoles = route.data['roles'] as Array<string>;
    //  console.log('requiredRoles', requiredRoles);
      const isAuthenticated = this.userSrv.getUserIdFromLocalStorage().ruoloId[0];
   //   console.log('isAuthenticated', isAuthenticated);
        // Converti requiredRoles in un array di numeri
  const requiredRolesNumbers = requiredRoles.map(role => parseInt(role, 10));

  // Controlla se isAuthenticated è incluso in requiredRolesNumbers
  const hasAuthorization = requiredRolesNumbers.includes(isAuthenticated);


      if (!hasAuthorization) {
        // Rindirizza l'utente a una pagina di errore o alla pagina di login
        this.router.navigate(['/']);
        return false;
      }
  
      return true;
    }
}