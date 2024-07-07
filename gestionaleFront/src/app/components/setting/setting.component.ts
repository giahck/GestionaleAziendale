import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../service/users.service';

@Component({
  selector: 'app-setting',
  templateUrl: './setting.component.html',
  styleUrl: './setting.component.scss'
})
export class SettingComponent implements OnInit {
 user!: any;
constructor(private userSrv:UsersService) { 
}
  ngOnInit(): void {
    this.user=this.userSrv.getUserIdFromLocalStorage();
  }

}
