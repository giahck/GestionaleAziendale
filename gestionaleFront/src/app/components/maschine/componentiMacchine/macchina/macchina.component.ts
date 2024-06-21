import { Component, Input } from '@angular/core';
import { Machine, Part, Piece } from '../../../../models/machin/machine.interface';

@Component({
  selector: 'app-macchina',
  templateUrl: './macchina.component.html',
  styleUrl: './macchina.component.scss'
})
export class MacchinaComponent {
  @Input() machines: Machine[] = [];
  @Input() pieces: Piece[] = [];
  @Input() parts: Part[] = [];

  selectedMachine: Machine | null = null;

  toggleParts(machine: Machine, event?: Event) {
    if (event) {
      event.stopPropagation();
    }
    if (this.selectedMachine === machine) {
      this.selectedMachine = null; // Se clicchi sulla macchina già selezionata, nascondi la card delle parti
    } else {
      this.selectedMachine = machine; // Altrimenti, mostra la card delle parti per la macchina selezionata
    }
  }

  isExpanded(machine: Machine) {
    return this.selectedMachine === machine;
  }
}
