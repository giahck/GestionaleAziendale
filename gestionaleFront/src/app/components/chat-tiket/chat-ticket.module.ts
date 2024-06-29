import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChatTicketRoutingModule } from './chat-ticket-routing.module';
import { FormsModule } from '@angular/forms';
import { ChatTiketComponent } from './chat-tiket.component';
import { SceltaMachineComponent } from './ticketComponenti/scelta-machine/scelta-machine.component';

@NgModule({
  declarations: [
   ChatTiketComponent,
   SceltaMachineComponent
  ],
  imports: [
    CommonModule,
    ChatTicketRoutingModule,
    FormsModule,
  ]
})
export class ChatTicketModule { }
