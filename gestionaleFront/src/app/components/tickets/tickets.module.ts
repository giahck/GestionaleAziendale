import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TicketsComponent } from './tickets.component';
import { CreateTicketsComponent } from './create-tickets/create-tickets.component';
import { TiketsRouterModule } from './tikets-router.module';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    TicketsComponent,
    CreateTicketsComponent
  ],
  imports: [
    CommonModule,
    TiketsRouterModule,
    ReactiveFormsModule

  ]
})
export class TicketsModule { }
