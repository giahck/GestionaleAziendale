import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChatTiketComponent } from './chat-tiket.component';

const routes: Routes = [
  {
    path: '',
    component: ChatTiketComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChatTicketRoutingModule { }
