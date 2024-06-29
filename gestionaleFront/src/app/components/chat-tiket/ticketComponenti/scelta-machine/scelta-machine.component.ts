import { machine } from 'node:os';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { RichiesteGetAllService } from '../../../../service/richieste-get-all.service';
import { MachineDto, PartDto, PieceDto, UserMachineDto } from '../../../../models/user-machine-dto.interface';
import { log } from 'node:console';

@Component({
  selector: 'app-scelta-machine',
  templateUrl: './scelta-machine.component.html',
  styleUrl: './scelta-machine.component.scss'
})
export class SceltaMachineComponent implements OnInit{
  userMachine: UserMachineDto[] = [];
activePart:boolean=false;
selectedMachine: MachineDto | null = null;
selectedParts: PartDto | null = null;
selectedPiece: PieceDto | null = null;
machineText: string = '';
partDescriptions: { [partId: number]: string } = {};
  @Input() testo: string = '';
  @Output() testoSelezionato = new EventEmitter<string>();
  constructor(private usMachine:RichiesteGetAllService ) {}
  ngOnInit(): void {
    this.usMachine.userMachine$.subscribe((userMachine: UserMachineDto[]) => {
      if (!userMachine || userMachine.length === 0) {
        this.usMachine.getUserMachine();
      } else {
        this.userMachine = userMachine;
     //   console.log('userMachine', userMachine);
      }
    });
  }
  selezionaDescrizioneMacchina(machine: MachineDto) {
    if (this.selectedMachine && this.selectedMachine.machineId === machine.machineId) {
      this.selectedMachine = null;
      this.machineText = '';
    } else {
      this.selectedMachine = machine;
      this.machineText = `Nome macchina: ${machine.nomeMacchina} Descrizione: ${machine.description} Marca: ${machine.marca}`;
    }
  
    this.updateTesto();
  }

  selezionaDescrizioneMacchinaParte(part: PartDto) {
    if (this.selectedParts && this.selectedParts.partId === part.partId) {
      this.selectedParts = null;
      delete this.partDescriptions[part.partId];
    } else {
      this.selectedParts = part;
      const testo = `Nome parte: ${part.nomeParte} Descrizione: ${part.descrizione} Note: ${part.note}\n`;
      this.partDescriptions[part.partId] = testo;
    }
  
    this.updateTesto();
  }

  selezionaDescrizioneMacchinaPartePiece(piece: PieceDto) {
    if (this.selectedPiece && this.selectedPiece.pieceId === piece.pieceId) {
      this.selectedPiece = null;
    } else {
      this.selectedPiece = piece;
      const testo = `Nome pezzo: ${piece.nomePezzo} Descrizione pezzo: ${piece.descrizione} Quantità pezzo: ${piece.quantityPiece}\n`;
      this.partDescriptions[this.selectedParts!.partId] += testo;
    }
  
    this.updateTesto();
  }

  private updateTesto() {
    this.testo = this.machineText + '\n' + Object.values(this.partDescriptions).join('');
    this.testoSelezionato.emit(this.testo);
  }
}
    

