import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { StatusMaschineComponent } from './status-maschine/status-maschine.component';
import { CreateMaschineComponent } from './create-maschine/create-maschine.component';
const routes: Routes = [
  {
    path: 'status-maschine',
    component: StatusMaschineComponent
  },
  {
    path: 'create-maschine',
    component: CreateMaschineComponent
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MashineRouteModule { }
