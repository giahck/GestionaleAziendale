import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MashineRouteModule } from './maschine-route.module';
import { MaschineComponent } from './maschine.component';
import { StatusMaschineComponent } from './status-maschine/status-maschine.component';
import { CreateMaschineComponent } from './create-maschine/create-maschine.component';

import { MacchinaComponent } from './componentiMacchine/macchina/macchina.component';
import { MenuCreateComponent } from './componentiMacchine/menu-create/menu-create.component';
import { ReactiveFormsModule } from '@angular/forms';




@NgModule({
  declarations: [
    MaschineComponent,
    StatusMaschineComponent,
    CreateMaschineComponent,
   MenuCreateComponent,
    MacchinaComponent,
  /*   PartiComponent, */
  
],
imports: [
  CommonModule,
  ReactiveFormsModule,
    MashineRouteModule
  ]
})
export class MashineModule { }
