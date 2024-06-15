import { Component, EventEmitter, HostListener, OnInit, Output } from '@angular/core';
import { AuthData } from '../../models/authData.interface';
import { AuthService } from '../../auth/auth.service';
import { navbarData } from './nav-data';
import { animate, keyframes, style, transition, trigger } from '@angular/animations';
interface SideNavToggle {
  screenWidth: number;
  collapsed: boolean;
}
@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.scss',
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [
        style({opacity: 0}),
        animate('350ms',
          style({opacity: 1})
        )
      ]),
      transition(':leave', [
        style({opacity: 1}),
        animate('350ms',
          style({opacity: 0})
        )
      ])
    ]),
    trigger('rotate', [
      transition(':enter', [
        animate('1000ms', 
          keyframes([
            style({transform: 'rotate(0deg)', offset: '0'}),
            style({transform: 'rotate(2turn)', offset: '1'})
          ])
        )
      ])
    ])
  ]
})
export class NavBarComponent implements OnInit{
  @Output() onToggleSideNav: EventEmitter<SideNavToggle> = new EventEmitter();
  @HostListener('window:resize', ['$event'])
  user!: AuthData | null
  screenWidth = 0;
  collapsed=true;
  navData=navbarData;
  constructor(private authSrv: AuthService) {}
  ngOnInit(): void {
    this.authSrv.user$.subscribe((user) => {
      this.user = user;
  });
    this.screenWidth = window.innerWidth;
}
onResize(event: any) {
  this.screenWidth = window.innerWidth;
  if(this.screenWidth <= 768 ) {
    this.collapsed = false;
    this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
  }
}

toggleCollapse(): void {
  this.collapsed = !this.collapsed;
  this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
}

closeSidenav(): void {
  this.collapsed = false;
  this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
}

  

  logout() {
    this.authSrv.logout();
}
}
