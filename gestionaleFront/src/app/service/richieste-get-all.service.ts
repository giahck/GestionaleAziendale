import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { BehaviorSubject } from 'rxjs';
import { UserMachineDto } from '../models/user-machine-dto.interface';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class RichiesteGetAllService {
  apiUrl=environment.apiURL;
  private userMachine = new BehaviorSubject<UserMachineDto[]>([]);
  userMachine$ = this.userMachine.asObservable();
  constructor(private http:HttpClient,private userSrv:UsersService) { }

  getUserMachine$(): UserMachineDto[] {
    return this.userMachine.getValue();
  }
  setUserMachine(userMachine: UserMachineDto[]): void {
    this.userMachine.next(userMachine);
   // console.log('userMachine', userMachine);
  }


  getUserMachine(): void {
    const user=this.userSrv.getUserIdFromLocalStorage();
    if (user.ruoloId[0] === 3 || user.ruoloId[0] === 5) {
      //const user = JSON.parse(user.id);
      this.http.get<UserMachineDto[]>(`${this.apiUrl}users/${user.id}/machine`).subscribe(
        (userMachine: UserMachineDto[]) => {
          this.setUserMachine(userMachine);
        }
      );
    } else {
      console.error('UserID non trovato nel localStorage');
    }
  }


}
