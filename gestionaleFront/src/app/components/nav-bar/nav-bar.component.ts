import { Router } from '@angular/router';
import { Component, EventEmitter, HostListener, OnInit, Output } from '@angular/core';
import { AuthData } from '../../models/authData.interface';
import { AuthService } from '../../auth/auth.service';
import { navbarData } from './nav-data';
import { animate, keyframes, style, transition, trigger } from '@angular/animations';
import { InavBarData } from './inav-bar-data.interface';
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
  collapsed=false;
  navData=navbarData;
multiple: boolean=false;
  constructor(private authSrv: AuthService,private router:Router) {}
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
  if(this.user){
  this.collapsed = !this.collapsed;
  this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
  }
}

closeSidenav(): void {
 
  this.collapsed = false;
  this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});

}
handleClick(item: InavBarData): void {
  this.shrinkItems(item);
  item.expanded = !item.expanded
}

getActiveClass(data: InavBarData): string {
  return this.router.url.includes(data.routeLink) ? 'active' : '';
}

shrinkItems(item: InavBarData): void {
  if (!this.multiple) {
    for(let modelItem of this.navData) {
      if (item !== modelItem && modelItem.expanded) {
        modelItem.expanded = false;
      }
    }
  }
}
  

  logout() {
    this.authSrv.logout();
}
}
