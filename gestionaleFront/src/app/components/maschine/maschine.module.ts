import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MashineRouteModule } from './maschine-route.module';
import { MaschineComponent } from './maschine.component';
import { StatusMaschineComponent } from './status-maschine/status-maschine.component';
import { CreateMaschineComponent } from './create-maschine/create-maschine.component';

import { MacchinaComponent } from './componentiMacchine/macchina/macchina.component';
import { MenuCreateComponent } from './componentiMacchine/menu-create/menu-create.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { MenuCreatePezziComponent } from './componentiMacchine/menu-create-pezzi/menu-create-pezzi.component';
import { MenuCreatePartiComponent } from './componentiMacchine/menu-create-parti/menu-create-parti.component';



@NgModule({
  declarations: [
    MaschineComponent,
    StatusMaschineComponent,
    CreateMaschineComponent,
   MenuCreateComponent,
    MacchinaComponent,
    MenuCreatePezziComponent,
    MenuCreatePartiComponent,
  /*   PartiComponent, */
  
],
imports: [
  CommonModule,
  ReactiveFormsModule,
    MashineRouteModule,
    FormsModule,
    
  ]
})
export class MashineModule { }
