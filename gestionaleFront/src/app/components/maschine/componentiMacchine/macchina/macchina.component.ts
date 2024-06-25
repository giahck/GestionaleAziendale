import { ChangeDetectorRef, Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { Machine, Part, Piece } from '../../../../models/machin/machine.interface';

@Component({
  selector: 'app-macchina',
  templateUrl: './macchina.component.html',
  styleUrl: './macchina.component.scss'
})
export class MacchinaComponent implements OnChanges {
  @Input() machines: Machine[] = [];
  @Input() pieces: Piece[] = [];
  @Input() parts: Part[] = [];
  @Output() machineSelected = new EventEmitter<Machine>();
  @Output() partSelected = new EventEmitter<Part>();
  selectedMachine: Machine | null = null;
  selectedPart: Part | null = null;
  constructor(private cdr: ChangeDetectorRef) { }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['machines']) {
      this.selectedMachine = null;
      this.selectedPart = null;
    }
  }
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
        this.partSelected.emit(undefined);
      } else {
        this.selectedPart = part;
        this.partSelected.emit(part);
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