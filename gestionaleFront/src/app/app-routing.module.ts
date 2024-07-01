import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './auth/login/login.component';

import { AuthGuard } from './auth/auth.guard';

import { SettingComponent } from './components/setting/setting.component';
import { UserGuard } from './user-guard.guard';


const routes: Routes = [
 {path: '', redirectTo: 'home', pathMatch: 'full'},
 {path: 'home', component: HomeComponent,canActivate: [AuthGuard],},
  {
    path: 'login',
    component: LoginComponent,
  },
  {path: 'chat-tiket',canActivate: [AuthGuard], 
    loadChildren: () => import('./components/chat-tiket/chat-ticket.module').then(m => m.ChatTicketModule)
  },
  {path: 'tickets' ,canActivate: [AuthGuard],
    loadChildren: () => import('./components/tickets/tickets.module').then(m => m.TicketsModule)
  },
  {path: 'maschines',canActivate: [AuthGuard,UserGuard],
    data: { roles: [3, 5] },
    loadChildren: () => import('./components/maschine/maschine.module').then(m => m.MashineModule)
  },
  {path: 'settings', component: SettingComponent,canActivate: [AuthGuard],},

/* { path: '**', redirectTo: '/login' } */

];

@NgModule({
  imports: [RouterModule.forRoot(routes),],
  exports: [RouterModule]
})
export class AppRoutingModule { }
