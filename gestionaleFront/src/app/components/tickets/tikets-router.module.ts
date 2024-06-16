import { CreateTicketsComponent } from './create-tickets/create-tickets.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TicketsComponent } from './tickets.component';
const routes: Routes = [
  {
    path: 'ticket',
    component: TicketsComponent
  },
  {
    path: 'create-tickets',
    component: CreateTicketsComponent
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TiketsRouterModule { }
