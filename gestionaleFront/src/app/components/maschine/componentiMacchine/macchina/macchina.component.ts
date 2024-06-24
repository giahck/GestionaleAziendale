import { ChangeDetectorRef, Component, EventEmitter, Input, Output } from '@angular/core';
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
  @Output() machineSelected = new EventEmitter<Machine>();
  selectedMachine: Machine | null = null;
  selectedPart: Part | null = null;
  constructor(private cdr: ChangeDetectorRef) { }
  toggleParts(machine: Machine, event?: Event) {
    if (event) {
      event.stopPropagation();
    }
    Promise.resolve().then(() => {
      if (this.selectedMachine === machine) {
        this.selectedMachine = null;
        this.selectedPart = null;
        this.machineSelected.emit(undefined);
      } else {
        this.selectedMachine = machine;
        this.selectedPart = null;
        this.machineSelected.emit(machine);
      }
      this.cdr.detectChanges(); 
    });
  }

  togglePezzi(part: Part, event?: Event) {
    if (event) {
      event.stopPropagation();
    }
    Promise.resolve().then(() => {
      if (this.selectedPart === part) {
        this.selectedPart = null;
      } else {
        this.selectedPart = part;
      }
      this.cdr.detectChanges(); 
    });
  }

  isExpandedPezzi(part: Part) {
    return this.selectedPart === part;
  }

  isExpanded(machine: Machine) {
    return this.selectedMachine === machine;
  }
}