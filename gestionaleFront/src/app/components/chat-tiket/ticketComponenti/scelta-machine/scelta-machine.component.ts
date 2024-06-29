import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { RichiesteGetAllService } from '../../../../service/richieste-get-all.service';
import { UserMachineDto } from '../../../../models/user-machine-dto.interface';
import { log } from 'node:console';

@Component({
  selector: 'app-scelta-machine',
  templateUrl: './scelta-machine.component.html',
  styleUrl: './scelta-machine.component.scss'
})
export class SceltaMachineComponent implements OnInit{
  userMachine: UserMachineDto[] = [];
 // testo='';
  @Input() testo: string = '';
  @Output() testoSelezionato = new EventEmitter<string>();
  constructor(private usMachine:RichiesteGetAllService ) {}
  ngOnInit(): void {
    this.usMachine.userMachine$.subscribe((userMachine: UserMachineDto[]) => {
      if (!userMachine || userMachine.length === 0) {
        this.usMachine.getUserMachine();
      } else {
        this.userMachine = userMachine;
        console.log('userMachine', userMachine);
      }
    });
  }
  selezionaDescrizione(testo: string) {
    console.log('testo', testo);
    this.testo += "\n"+testo;
    this.testoSelezionato.emit(this.testo);
  }
}
