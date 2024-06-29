import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { BehaviorSubject } from 'rxjs';
import { UserMachineDto } from '../models/user-machine-dto.interface';

@Injectable({
  providedIn: 'root'
})
export class RichiesteGetAllService {
  apiUrl=environment.apiURL;
  private userMachine = new BehaviorSubject<UserMachineDto[]>([]);
  userMachine$ = this.userMachine.asObservable();
  constructor(private http:HttpClient) { }

  getUserMachine$(): UserMachineDto[] {
    return this.userMachine.getValue();
  }
  setUserMachine(userMachine: UserMachineDto[]): void {
    this.userMachine.next(userMachine);
   // console.log('userMachine', userMachine);
  }
  getUserMachine(): void {
    const userId = localStorage.getItem('user');
    if (userId) {
      const user = JSON.parse(userId);
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
