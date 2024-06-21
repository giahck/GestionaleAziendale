import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './auth/login/login.component';

import { EmailVerifiedComponent } from './auth/email-verified/email-verified.component';
import { AuthGuard } from './auth/auth.guard';
import { ChatTiketComponent } from './components/chat-tiket/chat-tiket.component';
import { TicketsComponent } from './components/tickets/tickets.component';
import { MaschineComponent } from './components/maschine/maschine.component';
import { SettingComponent } from './components/setting/setting.component';

const routes: Routes = [
 {path: '', redirectTo: 'home', pathMatch: 'full'},
 {path: 'home', component: HomeComponent,canActivate: [AuthGuard],},
  {
    path: 'login',
    component: LoginComponent,
  },
  {path: 'chat-tiket', component: ChatTiketComponent,canActivate: [AuthGuard],},
  {path: 'tickets' ,canActivate: [AuthGuard],
    loadChildren: () => import('./components/tickets/tickets.module').then(m => m.TicketsModule)
  },
  {path: 'maschines',canActivate: [AuthGuard],
    loadChildren: () => import('./components/maschine/maschine.module').then(m => m.MashineModule)
  },
  {path: 'settings', component: SettingComponent,canActivate: [AuthGuard],},

/* { path: '**', redirectTo: '/login' } */

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
