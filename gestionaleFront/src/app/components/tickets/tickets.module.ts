import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TicketsComponent } from './tickets.component';
import { CreateTicketsComponent } from './create-tickets/create-tickets.component';
import { TiketsRouterModule } from './tikets-router.module';



@NgModule({
  declarations: [
    TicketsComponent,
    CreateTicketsComponent
  ],
  imports: [
    CommonModule,
    TiketsRouterModule

  ]
})
export class TicketsModule { }
