import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MashineRouteModule } from './maschine-route.module';
import { MaschineComponent } from './maschine.component';
import { StatusMaschineComponent } from './status-maschine/status-maschine.component';
import { CreateMaschineComponent } from './create-maschine/create-maschine.component';



@NgModule({
  declarations: [
    MaschineComponent,
    StatusMaschineComponent,
    CreateMaschineComponent
  ],
  imports: [
    CommonModule,
    MashineRouteModule
  ]
})
export class MashineModule { }
