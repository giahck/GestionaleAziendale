import { NgModule, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { StatusMaschineComponent } from './status-maschine/status-maschine.component';
import { CreateMaschineComponent } from './create-maschine/create-maschine.component';
import { AuthGuard } from '../../auth/auth.guard';
import { UserGuard } from '../../user-guard.guard';
const routes: Routes = [
  {
    path: 'status-maschine',
    component: StatusMaschineComponent
  },
  {
    path: 'create-maschine',
    component: CreateMaschineComponent,
    canActivate: [AuthGuard,UserGuard],
    data: { roles: [3, 5] },
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MashineRouteModule implements OnInit{
  constructor() { }
  ngOnInit(): void {
    
  }
 }
