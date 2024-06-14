import { Component } from '@angular/core';
import { AuthData } from '../../models/authData.interface';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.scss'
})
export class NavBarComponent {
  user!: AuthData | null
  constructor(private authSrv: AuthService) {}
  ngOnInit(): void {
    this.authSrv.user$.subscribe((user) => {
        this.user = user;
    });
}

  logout() {
    this.authSrv.logout();
}
}
