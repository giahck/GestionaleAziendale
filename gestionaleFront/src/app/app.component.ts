import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { Router } from '@angular/router';
interface SideNavToggle {
  screenWidth: number;
  collapsed: boolean;
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit{
  title = 'gestionaleFront';
  isSideNavCollapsed = false;
  screenWidth = 0;
  constructor(private authSrv: AuthService,private router: Router) {}
  ngOnInit(): void {
 //   console.log('AppComponent initialized');
    this.authSrv.restore();
  }
  onToggleSideNav(data: SideNavToggle): void {
    this.screenWidth = data.screenWidth;
    this.isSideNavCollapsed = data.collapsed;
  }
}
