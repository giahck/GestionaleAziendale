import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit{
  title = 'gestionaleFront';
  constructor(private authSrv: AuthService,private router: Router) {}
  ngOnInit(): void {
 //   console.log('AppComponent initialized');
    this.authSrv.restore();
  }
}
